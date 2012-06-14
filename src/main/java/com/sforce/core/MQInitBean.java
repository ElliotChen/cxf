package com.sforce.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ibm.mq.MQEnvironment;

@Component
public class MQInitBean implements InitializingBean {
	private static final Logger logger = LoggerFactory.getLogger(MQInitBean.class);
	@Value("${mq.host}")
	private String hostname;
	@Value("${mq.channel}")
	private String channel;
	@Value("${mq.port}")
	private int port;
	@Value("${mq.ccsid}")
	private int ccsid;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		MQEnvironment.hostname = this.hostname;
		MQEnvironment.channel = this.channel;
		MQEnvironment.port = this.port;
		MQEnvironment.CCSID = this.ccsid;
		logger.debug("Load Properties hostname[{}], channel[{}], port[{}], ccsid[{}] to MQEnvironment", new Object[] {this.hostname, this.channel, this.port, this.ccsid});
	}
	
	/*********************************************/
	/**			Getter/Setter					**/
	/*********************************************/
	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getCcsid() {
		return ccsid;
	}

	public void setCcsid(int ccsid) {
		this.ccsid = ccsid;
	}

	
	
}
