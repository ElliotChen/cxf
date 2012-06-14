package com.sforce.parser;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;

public class MQTest {
	@Ignore
	@Test
	public void testRead() throws Exception {
		MQEnvironment.hostname = "192.168.38.132";
		MQEnvironment.channel = "S_ec_a7c8a3d2f30e";
		MQEnvironment.port = 1414;
		MQEnvironment.CCSID = 950;
		MQGetMessageOptions options = new MQGetMessageOptions();
		options.options = MQC.MQGMO_NO_WAIT + MQC.MQGMO_CONVERT
				+ MQC.MQGMO_FAIL_IF_QUIESCING + MQC.MQGMO_SYNCPOINT;
		options.waitInterval = 15000;
		MQQueueManager mqQueueManager = new MQQueueManager("QM_ec_a7c8a3d2f30e");

		int receiveOptions = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT;
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
	
	@Test
	public void testWrite() throws Exception {
		MQEnvironment.hostname = "192.168.38.132";
		MQEnvironment.channel = "S_ec_a7c8a3d2f30e";
		MQEnvironment.port = 1414;
		MQEnvironment.CCSID = 950;
		MQGetMessageOptions options = new MQGetMessageOptions();
		options.options = MQC.MQGMO_WAIT + MQC.MQGMO_CONVERT
				+ MQC.MQGMO_FAIL_IF_QUIESCING + MQC.MQGMO_SYNCPOINT;
		options.waitInterval = 15000;
		MQQueueManager mqQueueManager = new MQQueueManager("QM_ec_a7c8a3d2f30e");

		int receiveOptions = MQC.MQOO_OUTPUT | MQC.MQOO_FAIL_IF_QUIESCING;
		System.out.println("RO:"+receiveOptions);
		MQQueue queue = mqQueueManager.accessQueue("EC", receiveOptions);

		MQMessage message = new MQMessage();
		message.format = MQC.MQFMT_STRING;
		message.messageId = getMessageId();
		
		message.write("abc".getBytes());
		queue.put(message);
		/*
		System.out.println(message.messageId);
		*/
		queue.close();
		mqQueueManager.disconnect();
	}
	
	private byte[] getMessageId() {
		SimpleDateFormat sdf = new SimpleDateFormat();
		return sdf.format(new Date()).getBytes();
	}
}
