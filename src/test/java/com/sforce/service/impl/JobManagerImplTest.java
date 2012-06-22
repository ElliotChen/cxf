package com.sforce.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sforce.domain.Job;
import com.sforce.domain.JobState;
import com.sforce.service.JobManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
@Transactional
public class JobManagerImplTest {
	@Autowired
	private JobManager jobManager;
	@Test
	@Rollback(false)
	public void test() {
		Job job = new Job();
		
		job = new Job();
		job.setOid("testreq01");
		job.setComponent("req01");
		job.setAbsolutePath("/Users/elliot/gitrepo/cxf/src/test/resources/req01.txt");
		job.setState(JobState.Created);
		job.setMqId("req01.txt");
		this.jobManager.create(job);
		
		job = new Job();
		job.setOid("testreq02");
		job.setComponent("req02");
		job.setAbsolutePath("/Users/elliot/gitrepo/cxf/src/test/resources/req02.txt");
		job.setState(JobState.Created);
		job.setMqId("req02.txt");
		this.jobManager.create(job);
		
		job = new Job();
		job.setOid("testreq03");
		job.setComponent("req03");
		job.setAbsolutePath("/Users/elliot/gitrepo/cxf/src/test/resources/req03.txt");
		job.setState(JobState.Created);
		job.setMqId("req03.txt");
		this.jobManager.create(job);
		
		job = new Job();
		job.setOid("testreq08");
		job.setComponent("req08");
		job.setAbsolutePath("/Users/elliot/gitrepo/cxf/src/test/resources/req08.txt");
		job.setState(JobState.Created);
		job.setMqId("req08.txt");
		this.jobManager.create(job);
		
	}

}
