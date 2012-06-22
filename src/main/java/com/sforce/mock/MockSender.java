package com.sforce.mock;

import com.sforce.intf.Sender;
import com.sforce.intf.impl.InitConfig;

public class MockSender implements Sender {

	@Override
	public boolean send() {
		return true;
	}

	public void init(InitConfig config) {
		
	}
}
