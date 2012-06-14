package com.sforce.service;

import com.sforce.domain.Job;

public interface JobManager extends BaseDomainService<Job, String> {
	Job occupyFirstJob(String component);
	void closeJob(String jobOid);
}
