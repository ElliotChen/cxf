package com.sforce.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sforce.dao.ExecutionDao;
import com.sforce.domain.Execution;
import com.sforce.service.ExecutionManager;
@Service("executionManager")
@Transactional(readOnly=true)
public class ExecutionManagerImpl extends
		AbstractDomainService<ExecutionDao, Execution, String> implements
		ExecutionManager {
	private static final Logger logger = LoggerFactory.getLogger(ExecutionManagerImpl.class);
	@Autowired
	private ExecutionDao dao;
	@Override
	public ExecutionDao getDao() {
		return this.dao;
	}

	@Override
	public Logger getLogger() {
		return logger;
	}

	@Override
	@Transactional(readOnly=false)
	public Execution findByComponent(String component) {
		Execution result = null;
		Execution example = new Execution();
		example.setComponent(component);
		
		List<Execution> list = this.dao.listByExample(example);
		if (list.isEmpty()) {
			result = new Execution();
			result.setComponent(component);
			this.dao.create(result);
		} else {
			result = list.get(0);
		}
		
		return result;
	}
}
