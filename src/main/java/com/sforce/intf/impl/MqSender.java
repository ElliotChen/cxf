package com.sforce.intf.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.mq.MQC;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.sforce.domain.Job;
import com.sforce.intf.Sender;
import com.sforce.service.JobManager;

public class MqSender implements Sender {
	private String component = "REQ01";
	private String queueManager = "QM_ec_a7c8a3d2f30e";
	private int option = 8208;
	private String queueName = "EC";
	@Autowired
	private JobManager jobManager;
	@Autowired
	private MQGetMessageOptions messageOptions;
	
	@Override
	public boolean send() {
		try {
		MQQueueManager mqQueueManager = new MQQueueManager(queueManager);
		MQQueue queue = mqQueueManager.accessQueue(queueName, option);
		Job job = null;
		List<MQMessage> messages = new ArrayList<MQMessage>();
		while ((job = jobManager.occupyFirstJob(component)) != null) {
			
		}
		MQMessage message = new MQMessage();
		message.format = MQC.MQFMT_STRING;
		message.write("abc".getBytes());
		for ()
		queue.put(message);
		queue.close();
		mqQueueManager.disconnect();
		} catch (Exception e) {
			
		}
		return false;
	}

	@Override
	public boolean initSource(List<File> source) {
		return false;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getQueueManager() {
		return queueManager;
	}

	public void setQueueManager(String queueManager) {
		this.queueManager = queueManager;
	}

	public int getOption() {
		return option;
	}

	public void setOption(int option) {
		this.option = option;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public MQGetMessageOptions getMessageOptions() {
		return messageOptions;
	}

	public void setMessageOptions(MQGetMessageOptions messageOptions) {
		this.messageOptions = messageOptions;
	}
	
	

}
