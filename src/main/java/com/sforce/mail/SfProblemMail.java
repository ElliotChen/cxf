package com.sforce.mail;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.sforce.domain.Job;

public class SfProblemMail implements Runnable{
	private static final Logger logger = LoggerFactory.getLogger(SfProblemMail.class);
	private String sender;
	private String[] receivers;
	private Job job;
	private JavaMailSender javaMailSender;
	
	public SfProblemMail(String sender, String[] receivers, Job job,
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
			helper.setSubject("SFDC Problem of ["+job.getComponent()+"]");
			File source = new File(job.getAbsolutePath());
			
			if (source.exists()) {
				helper.addAttachment("source", source);
			}
			
			if (StringUtils.isNotEmpty(job.getErrorPath())) {
				File err = new File(job.getErrorPath());
				if (err.exists()) {
					helper.addAttachment("error", err);
				}
			}
			
			this.javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {
			logger.error("Mail Sending Exception!", e);
		}
		
	}

}
