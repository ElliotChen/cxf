package com.sforce.mock;

import java.io.File;
import java.util.List;

import com.sforce.intf.Receiver;

public class MockReceiver implements Receiver {

	@Override
	public boolean receive() {
		return true;
	}

	@Override
	public List<File> getResult() {
		return null;
	}

}
