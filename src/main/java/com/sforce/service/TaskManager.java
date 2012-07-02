package com.sforce.service;

import com.ibm.mq.MQException;
import com.sforce.domain.Job;
import com.sforce.intf.impl.MqConnector;

public interface TaskManager {
	void arrange(Runnable task);
	void mailMqProblem(MQException me, MqConnector connector, String[] receivers);
	void mailSfProblems(Job job, String[] receiver);
}
