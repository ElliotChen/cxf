package com.sforce.mock;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.intf.Receiver;
import com.sforce.intf.impl.BaseComponent;
import com.sforce.to.InitConfig;

public class MockReceiver implements Receiver {
	private static final Logger logger = LoggerFactory.getLogger(MockReceiver.class);
	@Override
	public boolean receive() {
		logger.info("Try to sleep!");
		/*
		try {
			Thread.sleep(15*1000);
		} catch (InterruptedException e) {
		}
		*/
		logger.info("Wakeup!");
		return true;
	}

	@Override
	public List<File> getResult() {
		return null;
	}

	public void init(InitConfig config) {
		
	}
}
