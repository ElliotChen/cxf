package com.sforce.service;

import java.util.List;

import com.sforce.domain.Job;

public interface JobManager extends BaseDomainService<Job, String> {
	Job occupyFirstJob(String component);
	void finish(Job job, List<String> errors, String[] receivers);
	void release(Job job);
	void abandon(Job job, List<String> errors, String[] receivers);
	void reset(Job job);
	void cleanOldJobs();
}
