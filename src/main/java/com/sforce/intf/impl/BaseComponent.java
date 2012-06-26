package com.sforce.intf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.sforce.intf.Component;
import com.sforce.intf.Receiver;
import com.sforce.intf.Sender;
import com.sforce.intf.Status;
import com.sforce.to.InitConfig;

public class BaseComponent implements Component, InitializingBean {
	private static final Logger logger = LoggerFactory.getLogger(BaseComponent.class);
	private Receiver receiver;
	private Sender sender;
	private String name;
	private Status status;
	private Boolean debug;
	private String info;
	@Override
	public Status getStatus() {
		return status;
	}
	@Override
	public Boolean init() {
		logger.info("Initializing [{}].", this.name);
		try {
			InitConfig config = new InitConfig();
			config.setName(name);
			config.setDebugMode(debug);
			receiver.init(config);
			sender.init(config);
			this.status = Status.Available;
		} catch (Exception e) {
			logger.error("Initial Failed!", e);
			this.status = Status.InitFailed;
		}
		logger.info("[{}] initialized.", this.name);
		return Boolean.TRUE;
	}
	@Override
	public void run() {
		if (!this.isAvailable()) {
			logger.info("Sorry, [{}] not available!", this.name);
			return;
		}
		logger.info("{} runs!",this.name);
		this.status = Status.Reading;
		try {
			logger.info("{} now is Reading!",this.name);
			this.receiver.receive();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.status = Status.Sending;
		try {
			logger.info("{} now is Sending!",this.name);
			this.sender.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("{} now is Finished!",this.name);
		this.status = Status.Available;
//		return true;
	}
	@Override
	public Boolean isAvailable() {
		return Status.Available.equals(status);
	}
	public Receiver getReceiver() {
		return receiver;
	}
	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}
	public Sender getSender() {
		return sender;
	}
	public void setSender(Sender sender) {
		this.sender = sender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfo() {
		return this.info;
	}
	public Boolean getDebug() {
		return debug;
	}
	public void setDebug(Boolean debug) {
		this.debug = debug;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		this.init();
	}

}
