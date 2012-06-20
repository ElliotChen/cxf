package com.sforce.mock;

import java.io.File;
import java.util.List;

import com.sforce.intf.Sender;

public class MockSender implements Sender {

	@Override
	public boolean send() {
		return true;
	}

}
