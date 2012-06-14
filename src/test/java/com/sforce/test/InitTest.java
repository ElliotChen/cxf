package com.sforce.test;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ibm.mq.MQC;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.sforce.intf.impl.MqReceiver;
import com.sforce.intf.impl.MqSender;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class InitTest {
	@Autowired
	private MqReceiver receiver;
	
	@Autowired
	private MqSender sender;
	@Test
	public void testReceiver() {
//		receiver.receive();
		sender.send();
	}
	@Ignore
	@Test
	public void test() throws Exception {
		MQGetMessageOptions options = new MQGetMessageOptions();
		options.options = MQC.MQGMO_WAIT + MQC.MQGMO_CONVERT
				+ MQC.MQGMO_FAIL_IF_QUIESCING + MQC.MQGMO_SYNCPOINT;
		options.waitInterval = 15000;
		MQQueueManager mqQueueManager = new MQQueueManager("QM_ec_a7c8a3d2f30e");

		int receiveOptions = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT;
		System.out.println("RO:"+receiveOptions);
		MQQueue queue = mqQueueManager.accessQueue("EC", receiveOptions);

		MQMessage message = new MQMessage();
		queue.get(message, options);
		System.out.println(new String(message.messageId));
		int length = message.getMessageLength();
		byte[] data = new byte[length];
		message.readFully(data);
		System.out.println(new String(data));
		queue.close();
		mqQueueManager.disconnect();
	}
}
