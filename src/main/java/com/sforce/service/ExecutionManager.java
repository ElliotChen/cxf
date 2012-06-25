package com.sforce.service;

import com.sforce.domain.Execution;

public interface ExecutionManager extends BaseDomainService<Execution, String> {
	public Execution findByComponent(String component);
}
