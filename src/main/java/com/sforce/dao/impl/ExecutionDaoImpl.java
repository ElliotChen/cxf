package com.sforce.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sforce.dao.ExecutionDao;
import com.sforce.domain.Execution;

@Repository("executionDao")
public class ExecutionDaoImpl extends AbstractBaseDao<Execution, String>
		implements ExecutionDao {
	private static final Logger logger = LoggerFactory.getLogger(ExecutionDaoImpl.class);
	@Override
	public Logger getLogger() {
		return logger;
	}

	

}
