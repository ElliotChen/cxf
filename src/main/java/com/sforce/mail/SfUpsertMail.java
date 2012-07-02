package com.sforce.mail;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.sforce.domain.Job;

public class SfUpsertMail implements Runnable{
	private static final Logger logger = LoggerFactory.getLogger(SfUpsertMail.class);
	private String sender;
	private String[] receivers;
	private Job job;
	private JavaMailSender javaMailSender;
	
	public SfUpsertMail(String sender, String[] receivers, Job job,
			JavaMailSender javaMailSender) {
		super();
		this.sender = sender;
		this.receivers = receivers;
		this.job = job;
		this.javaMailSender = javaMailSender;
	}

	@Override
	public void run() {
		
		MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(sender);
			helper.setTo(this.receivers);
			helper.setSubject("SFDC Error Log For ["+job.getComponent()+"]");
			helper.addAttachment("source", new File(job.getAbsolutePath()));
			helper.addAttachment("error", new File(job.getErrorPath()));
			this.javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {
			logger.error("Mail Sending Exception!", e);
		}
		
	}

}
