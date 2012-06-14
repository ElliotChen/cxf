package com.sforce.parser;

import org.junit.Test;

import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;

public class MQTest {
	@Test
	public void test() throws Exception {
		MQEnvironment.hostname = "192.168.38.132";
		MQEnvironment.channel = "S_ec_a7c8a3d2f30e";
		MQEnvironment.port = 1414;
		MQEnvironment.CCSID = 950;
		MQGetMessageOptions options = new MQGetMessageOptions();
		options.options = MQC.MQGMO_WAIT + MQC.MQGMO_CONVERT
				+ MQC.MQGMO_FAIL_IF_QUIESCING + MQC.MQGMO_SYNCPOINT;
		options.waitInterval = 15000;
		MQQueueManager mqQueueManager = new MQQueueManager("QM_ec_a7c8a3d2f30e");

		int receiveOptions = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT;
		MQQueue queue = mqQueueManager.accessQueue("EC", receiveOptions);

		MQMessage mqMessage = new MQMessage();
		queue.get(mqMessage, options);
		System.out.println(mqMessage.messageId);

		queue.close();
		mqQueueManager.disconnect();
	}
}
