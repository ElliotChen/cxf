package com.sforce.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.sforce.core.MQInitBean;
import com.sforce.intf.impl.MqConnector;

public class MqConnectMail implements Runnable {
	private MqConnector connector;
	private MQInitBean mqInitBean;
	private String sender;
	private String[] receivers;
	private JavaMailSender javaMailSender;
	
	public MqConnectMail(JavaMailSender javaMailSender, MqConnector connector, MQInitBean mqInitBean, String sender, String[] receivers) {
		this.javaMailSender = javaMailSender;
		this.connector = connector;
		this.mqInitBean = mqInitBean;
		this.sender = sender;
		this.receivers = receivers;
	}
	
	@Override
	public void run() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("Can not connect to Server["+mqInitBean.getHostname()+"] QueueManager["+connector.getQueueManagerName()+"] - Queue["+connector.getQueueName()+"]");
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("MQ Connection Problem");
		message.setText(sb.toString());
		message.setFrom(sender);
		message.setTo(receivers);
		
		javaMailSender.send(message);
	}
	
}
