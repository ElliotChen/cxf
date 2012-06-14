package com.sforce.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ibm.mq.MQC;
import com.ibm.mq.MQGetMessageOptions;

@Configuration
public class ApplicationConfig {
	@Bean(name="messageOptions")
	public MQGetMessageOptions getMessageOptions() throws Exception {
		MQGetMessageOptions options = new MQGetMessageOptions();
		options.options = MQC.MQGMO_WAIT + MQC.MQGMO_CONVERT
				+ MQC.MQGMO_FAIL_IF_QUIESCING + MQC.MQGMO_SYNCPOINT;
		options.waitInterval = 15000;
		return options;
	}
	
	
}
