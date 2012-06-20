package com.sforce.intf.impl;

import java.io.File;
import java.util.List;

import com.sforce.intf.Sender;

public class SfSender extends SfConnector implements Sender {

	@Override
	public boolean send() {
		if (!this.connected) {
			return false;
		}
		
		
		return false;
	}

	@Override
	public boolean initSource(List<File> source) {
		return false;
	}

}
