package com.sforce.intf.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.mq.MQC;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.sforce.domain.Job;
import com.sforce.intf.Sender;
import com.sforce.service.JobManager;
import com.sforce.util.DateUtils;

public class MqSender extends MqConnector implements Sender {
	private static final Logger logger = LoggerFactory.getLogger(MqSender.class);
	@Autowired
	private JobManager jobManager;
	@Autowired
	private MQGetMessageOptions messageOptions;
	
	@Override
	public boolean send() {
		logger.info("Executing MqSender.send() of Component[{}]", this.component);
		MQQueueManager mqQueueManager = null;
		MQQueue queue = null;
		try {
			mqQueueManager = new MQQueueManager(queueManagerName);
			queue = mqQueueManager.accessQueue(queueName, option);

			Job job = null;
			List<MQMessage> messages = new ArrayList<MQMessage>();
			while ((job = jobManager.occupyFirstJob(component)) != null) {
				logger.info("Find Sending : {}", job);
				File source = new File(job.getAbsolutePath());
				if (!source.exists()) {
					// TODO
					continue;
				}
				byte[] messageId = this.genMessageId();
				this.split(source, messages, messageId);

				for (MQMessage messge : messages) {
					queue.put(messge);
				}
				logger.info("Component[{}] add [{}] messages with id[{}]", new Object[] {this.component, messages.size(), new String(messageId)});
				
				logger.info("Closing {}", job);
				this.jobManager.closeJob(job.getOid());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeQueue(queue);
			closeQueueManager(mqQueueManager);
		}
		
		return false;
	}
	
	public MQGetMessageOptions getMessageOptions() {
		return messageOptions;
	}

	public void setMessageOptions(MQGetMessageOptions messageOptions) {
		this.messageOptions = messageOptions;
	}
	protected byte[] genMessageId() {
		String sId = this.component +"_"+ DateUtils.formatMessageId(new Date());
		return sId.getBytes();
	}
	protected void split(File source, List<MQMessage> messages, byte[] messageId) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(source);
			int length = 0;
			byte[] bs = new byte[1024*64];
			MQMessage message = null;
			int seq = 1;
			while ((length = fis.read(bs)) >= 0) {
				byte[] data = ArrayUtils.subarray(bs, 0, length);
				message = new MQMessage();
				message.format = MQC.MQFMT_STRING;
				message.messageId = messageId;
				message.correlationId = "M".getBytes();
				message.write(data);
				messages.add(message);
			}
			
			if (null != message) {
				message.correlationId = "E".getBytes();
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			if (null != fis) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}

}
