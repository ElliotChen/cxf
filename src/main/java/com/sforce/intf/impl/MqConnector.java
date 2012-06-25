package com.sforce.intf.impl;

import com.ibm.mq.MQException;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;

public abstract class MqConnector {
	protected String component;
	protected String queueManagerName;
	protected int option = 8208;
	protected String queueName;
	
	protected Boolean debugMode = Boolean.FALSE;
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public String getQueueManagerName() {
		return queueManagerName;
	}
	public void setQueueManagerName(String queueManagerName) {
		this.queueManagerName = queueManagerName;
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
	
	public void closeQueue(MQQueue queue) {
		if (null == queue) {
			return;
		}
		
		try {
			queue.close();
		} catch (MQException e) {
			e.printStackTrace();
		}
	}
	
	public void closeQueueManager(MQQueueManager manager) {
		if (null == manager) {
			return;
		}
		
		try {
			manager.disconnect();
		} catch (MQException e) {
			e.printStackTrace();
		}
	}
	public Boolean getDebugMode() {
		return debugMode;
	}
	public void setDebugMode(Boolean debugMode) {
		this.debugMode = debugMode;
	}
	
	
}
