package com.sforce.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sforce.dao.JobDao;
import com.sforce.domain.Job;
import com.sforce.domain.JobState;
import com.sforce.domain.support.Condition;
import com.sforce.domain.support.LikeMode;
import com.sforce.domain.support.OperationEnum;
import com.sforce.domain.support.SimpleCondition;
import com.sforce.service.JobManager;
import com.sforce.service.TaskManager;

@Service("jobManager")
@Transactional(readOnly=true)
public class JobManagerImpl extends AbstractDomainService<JobDao, Job, String>
		implements JobManager {
	private static final Logger logger = LoggerFactory.getLogger(JobManagerImpl.class);
	@Autowired
	private JobDao dao;
	
	@Autowired
	private TaskManager taskManager;
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
		example.setState(JobState.Created);
		List<Job> jobs = this.dao.listByExample(example, null, LikeMode.NONE, new String[] {"createdDate"}, null);
		logger.debug("Find Jobs size[{}] for component[{}]", jobs.size(), component);
		if (jobs.isEmpty()) {
			return null;
		}
		
		Job job = jobs.get(0);
		job.setState(JobState.Processing);
		this.dao.update(job);
		
		return job;
	}
	
	@Transactional(readOnly=false)
	public void finish(Job job, List<String> errors, String[] receivers) {
		/*
		Job job = this.dao.findByOid(jobOid);
		if (null == job) {
			logger.error("Can't find Job with oid[{}]", jobOid);
			return;
		}
		*/
		if (null == job) {
			logger.warn("Finish Job can't accept empty Job");
			return;
		}
		if (!errors.isEmpty()) {
			File efile = new File(job.getAbsolutePath()+".error");
			try {
				FileUtils.writeLines(efile, errors, false);
			} catch (IOException e) {
				logger.error("");
			}
			job.setErrorPath(efile.getAbsolutePath());
			taskManager.mailSfProblems(job, receivers);
		}
		job.setState(JobState.Closed);
		
		this.dao.update(job);
	}
	
	@Transactional(readOnly=false)
	public void release(Job job) {
		if (null == job) {
			logger.warn("Release Job can't accept empty Job");
			return;
		}
		job.setState(JobState.Created);
		this.dao.update(job);
	}
	
	@Transactional(readOnly=false)
	public void abandon(Job job, List<String> errors, String[] receivers) {
		if (null == job) {
			logger.warn("Abandon Job can't accept empty Job");
			return;
		}
		if (!errors.isEmpty()) {
			File efile = new File(job.getAbsolutePath()+".error");
			try {
				FileUtils.writeLines(efile, errors, false);
			} catch (IOException e) {
				logger.error("");
			}
			job.setErrorPath(efile.getAbsolutePath());
			taskManager.mailSfProblems(job, receivers);
		}
		job.setState(JobState.Abandon);
		this.dao.update(job);
	}
	
	@Transactional(readOnly=false)
	public void reset(Job job) {
		job.setState(JobState.Created);
		this.dao.update(job);
	}
	
	@Transactional(readOnly=false)
	public void cleanOldJobs() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -3);
		List<Condition> conditions = new ArrayList<Condition>();
		conditions.add(new SimpleCondition("createdDate", cal.getTime(), OperationEnum.LE));
		List<Job> jobs = this.dao.listByExample(new Job(), conditions, LikeMode.NONE, null, null);
		logger.info("Finding [{}] Jobs to delete.", jobs.size());
		for (Job job : jobs) {
			logger.info("Delete Job [{}]", job);
			this.dao.delete(job);
		}
	}
}
