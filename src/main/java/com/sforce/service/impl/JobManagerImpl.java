package com.sforce.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sforce.dao.JobDao;
import com.sforce.domain.Job;
import com.sforce.domain.JobState;
import com.sforce.service.JobManager;

@Service("jobManager")
public class JobManagerImpl extends AbstractDomainService<JobDao, Job, String>
		implements JobManager {
	private static final Logger logger = LoggerFactory.getLogger(JobManagerImpl.class);
	@Autowired
	private JobDao dao;
	@Override
	public JobDao getDao() {
		return dao;
	}

	@Override
	public Logger getLogger() {
		return logger;
	}

	@Transactional(readOnly=false)
	public Job occupyFirstJob(String component) {
		Job example = new Job();
		example.setComponent(component);
		
		List<Job> jobs = this.dao.listByExample(example, null, null, new String[] {"createdDate"}, null);
		
		if (jobs.isEmpty()) {
			return null;
		}
		
		Job job = jobs.get(0);
		job.setState(JobState.Processing);
		this.dao.update(job);
		
		return job;
	}
}
