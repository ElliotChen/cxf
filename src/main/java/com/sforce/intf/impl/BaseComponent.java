package com.sforce.intf.impl;

import com.sforce.intf.Component;
import com.sforce.intf.Receiver;
import com.sforce.intf.Sender;
import com.sforce.intf.Status;

public class BaseComponent implements Component {
	private Receiver receiver;
	private Sender sender;
	
	private Status status;
	@Override
	public Status getStatus() {
		return status;
	}
	@Override
	public Boolean init() {
		return null;
	}
	@Override
	public Boolean trigger() {
		this.status = Status.Reading;
		try {
			this.receiver.receive();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.status = Status.Sending;
		try {
			this.sender.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.status = Status.Available;
		return true;
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
	
}
