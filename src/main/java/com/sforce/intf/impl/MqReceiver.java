package com.sforce.intf.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.sforce.intf.Receiver;

public class MqReceiver implements Receiver {
	@Autowired
	private MQGetMessageOptions messageOptions;
	private String queueManager = "QM_ec_a7c8a3d2f30e";
	private int option = 1;
	private String queueName = "EC";
	private List<File> files = new ArrayList<File>();

	@Override
	public boolean receive() {

		try {
			MQQueueManager mqQueueManager = new MQQueueManager(queueManager);
			MQQueue queue = mqQueueManager.accessQueue(queueName, option);
			MQMessage message = new MQMessage();
			queue.get(message, messageOptions);
			System.out.println(Base64.encodeBase64URLSafeString(message.messageId));
			int length = message.getMessageLength();
			byte[] data = new byte[length];
			message.readFully(data);
			System.out.println(new String(data));
			queue.close();
			mqQueueManager.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public List<File> getResult() {
		return files;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
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

	public MQGetMessageOptions getMessageOptions() {
		return messageOptions;
	}

	public void setMessageOptions(MQGetMessageOptions messageOptions) {
		this.messageOptions = messageOptions;
	}

}
