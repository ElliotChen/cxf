package com.sforce.intf.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sforce.domain.Execution;
import com.sforce.intf.Receiver;
import com.sforce.parser.Parser;
import com.sforce.service.ExecutionManager;

public class SfReceiver extends SfConnector implements Receiver {
	protected List<Parser<?>> parsers;
	@Autowired
	protected ExecutionManager executionManager;
	@Override
	public boolean receive() {
		Execution execution = executionManager.findByComponent(component);
		Date lastDate = execution.getLastSuccessDate();
		
		return false;
	}

	@Override
	public List<File> getResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(InitConfig config) {
		this.component = config.getName();
		this.debugMode = config.getDebugMode();
		if (null != this.parsers) {
			for (Parser<?> parser : this.parsers) {
				parser.init();
			}
		}
	}
}
