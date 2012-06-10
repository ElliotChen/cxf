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
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Boolean trigger() {
		return null;
	}
	@Override
	public Boolean isAvailable() {
		return Status.Available.equals(status);
	}
	
	
}
