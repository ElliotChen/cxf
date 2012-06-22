package com.sforce.service.impl;

import org.springframework.core.task.TaskExecutor;

import com.sforce.service.TaskManager;

public class TaskManagerImpl implements TaskManager {
	private TaskExecutor taskExecutor;
	
	public void arrange(Runnable task) {
		this.taskExecutor.execute(task);
	}
}
