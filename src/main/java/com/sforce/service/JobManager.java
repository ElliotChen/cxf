package com.sforce.service;

import com.sforce.domain.Job;

public interface JobManager extends BaseDomainService<Job, String> {
	Job occupyFirstJob(String component);
	void finish(Job job);
	void release(Job job);
	void abandon(Job job);
}
