package com.sforce.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sforce.dao.JobDao;
import com.sforce.domain.Job;

@Repository("jobDao")
public class JobDaoImpl extends AbstractBaseDao<Job, String> implements JobDao {
	private static final Logger logger = LoggerFactory.getLogger(JobDaoImpl.class);
	@Override
	public Logger getLogger() {
		return logger;
	}

	

}
