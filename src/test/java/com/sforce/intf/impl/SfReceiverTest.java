package com.sforce.intf.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sforce.soap.enterprise.sobject.Opportunity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class SfReceiverTest {
	@Autowired
	@Qualifier("req05Receiver")
	private SfReceiver receiver;
	@Test
	public void test() {
		receiver.connect();
		receiver.receive();
		receiver.disconnect();
		Opportunity op = null;
	}

}
