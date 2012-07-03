package com.sforce.intf.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.sforce.domain.Job;
import com.sforce.domain.JobState;
import com.sforce.intf.Receiver;
import com.sforce.service.JobManager;
import com.sforce.service.TaskManager;
import com.sforce.to.InitConfig;
import com.sforce.util.DateUtils;

public class MqReceiver extends MqConnector implements Receiver {
	private static final Logger logger = LoggerFactory.getLogger(MqReceiver.class);
	@Autowired
	private MQGetMessageOptions messageOptions;
	@Autowired
	private JobManager jobManager;
	@Autowired
	private TaskManager taskManager;
	
	private String[] receivers;
	@Value("${file.parent.path}")
	private String parentPath;
	private List<File> files = new ArrayList<File>();

	@Override
	public boolean receive() {
		logger.info("Executing MqReceiver.receive() of Component[{}]", this.component);
		MQQueueManager mqQueueManager = null;
		MQQueue queue = null;
		try {
			mqQueueManager = new MQQueueManager(queueManagerName);
			queue = mqQueueManager.accessQueue(queueName, option);

			boolean empty = false;
			byte[] lastMessageId = new byte[0];
			List<Job> jobs = new ArrayList<Job>();
			while (!empty) {
				MQMessage message = new MQMessage();
				try {
					queue.get(message, messageOptions);
				} catch (MQException mqe) {
					logger.warn("MQException Code[{}]", mqe.reasonCode);
					empty = true;
					break;
				}
				byte[] messageId = message.messageId;
				logger.debug("Message Info  cid[{}], seq[{}]", message.correlationId,message.messageSequenceNumber);
				if (!Arrays.equals(messageId, lastMessageId)) {
					initNewJob(jobs, message);
					lastMessageId = messageId;
				} else {
					appendData(jobs, message);
				}
			}
			
			for (Job job : jobs) {
				jobManager.create(job);
				logger.info("Create {} for Component[{}]", job, this.component);
			}
			
		} catch(MQException me) {
			this.taskManager.mailMqProblem(me, this, receivers);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeQueue(queue);
			closeQueueManager(mqQueueManager);
		}
		return true;
	}

	private void initNewJob(List<Job> jobs, MQMessage message) {
		String messageId = new String(message.messageId).trim();
		Job job = new Job();
		job.setComponent(component);
		job.setMqId(messageId);
		job.setState(JobState.Created);
		String folderPath = this.parentPath + "/"+this.component+"/"+DateUtils.formatPath(new Date())+"/";
		String fileName = messageId;

//		String filePath = folderPath+messageId+".txt";
		File file = new File(folderPath, fileName);
		if (file.exists()) {
			logger.error("Can't override exist file[{}]", file.getAbsolutePath());
			//TODO
			return;
		}
		try {
			job.setAbsolutePath(file.getAbsolutePath());
			byte[] data = new byte[message.getMessageLength()];
			message.readFully(data);
			FileUtils.writeByteArrayToFile(file, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		jobs.add(job);
	}
	
	private void appendData(List<Job> jobs, MQMessage message) {
		String messageId = new String(message.messageId);
		Job job = null;
		for (Job iter : jobs) {
			if (messageId.equals(iter.getMqId())) {
				job = iter;
				break;
			}
		}

		if (null == job || StringUtils.isEmpty(job.getAbsolutePath())) {
			logger.error("Can't append data with messageId[{}]", messageId);
			return;
		}

		try {
			File target = new File(job.getAbsolutePath());
			byte[] data = new byte[message.getMessageLength()];
			message.readFully(data);
			FileUtils.writeByteArrayToFile(target, data, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<File> getResult() {
		return files;
	}

	public void init(InitConfig config) {
		this.component = config.getName();
		this.debugMode = config.getDebugMode();
	}

	public String[] getReceivers() {
		return receivers;
	}

	public void setReceivers(String[] receivers) {
		this.receivers = receivers;
	}

	public JobManager getJobManager() {
		return jobManager;
	}

	public void setJobManager(JobManager jobManager) {
		this.jobManager = jobManager;
	}

	public TaskManager getTaskManager() {
		return taskManager;
	}

	public void setTaskManager(TaskManager taskManager) {
		this.taskManager = taskManager;
	}

	public String getParentPath() {
		return parentPath;
	}

	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}
	
	
	
}
