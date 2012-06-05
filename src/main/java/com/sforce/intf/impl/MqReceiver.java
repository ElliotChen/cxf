package com.sforce.intf.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.sforce.intf.Receiver;

public class MqReceiver implements Receiver {
	private String queueName;
	private List<File> files = new ArrayList<File>();
	@Override
	public boolean receive() {
		files.clear();
		//Connection mq and get files
		files.add(new File("/Users/elliot/gitrepo/cxf/src/test/resources/req09.txt"));
		return true;
	}

	@Override
	public List<File> getResult() {
		return files;
	}

}
