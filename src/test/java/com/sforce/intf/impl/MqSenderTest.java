package com.sforce.intf.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sforce.domain.Job;
import com.sforce.domain.JobState;
import com.sforce.service.JobManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
@Transactional
public class MqSenderTest {
	@Autowired
	private MqSender sender;
	
	@Autowired
	private JobManager jobManager;
	
	@Test
	public void test() {
		Job job = new Job();
		job.setComponent(sender.getComponent());
		job.setAbsolutePath("/Users/elliot/Downloads/jquery.ui.ufd-0.6/examples/testCases.html");
		job.setState(JobState.Created);
		this.jobManager.create(job);
		
		this.sender.send();
		
	}

}
