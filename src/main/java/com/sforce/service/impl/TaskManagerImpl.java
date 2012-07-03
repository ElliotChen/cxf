package com.sforce.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.ibm.mq.MQException;
import com.sforce.core.MQInitBean;
import com.sforce.domain.Job;
import com.sforce.intf.impl.MqConnector;
import com.sforce.mail.MqConnectMail;
import com.sforce.mail.SfProblemMail;
import com.sforce.service.TaskManager;

@Component("taskManager")
public class TaskManagerImpl implements TaskManager {
	private static final Logger logger = LoggerFactory.getLogger(TaskManagerImpl.class);
	@Autowired
	private TaskExecutor taskExecutor;
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private MQInitBean mqInitBean;
	@Value("${mail.sender}")
	private String sender;
	@Value("${mail.enable}")
	private Boolean enable;
	@Override
	public void arrange(Runnable task) {
		this.taskExecutor.execute(task);
	}
	
	@Override
	public void mailMqProblem(MQException me, MqConnector connector, String[] receivers) {
		logger.info("Send Mail for MQ Conntion Problem.");
		if (enable) {
			return;
		}
		MqConnectMail mail = new MqConnectMail(javaMailSender, connector, mqInitBean, sender, receivers);
		this.taskExecutor.execute(mail);
	}
	
	@Override
	public void mailSfProblems(Job job, String[] receivers) {
		logger.info("Send Mail for Salesforce Problem.");
		if (enable) {
			return;
		}
		SfProblemMail mail = new SfProblemMail(sender, receivers, job, javaMailSender);
		this.taskExecutor.execute(mail);
	}
}
