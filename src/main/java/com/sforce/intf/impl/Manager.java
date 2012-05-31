package com.sforce.intf.impl;

import com.sforce.intf.Receiver;
import com.sforce.intf.Sender;

public class Manager {
	private Receiver receiver;
	private Sender sender;
	public void execute() {
		if (this.receiver.isAvailable()) {
			if (this.receiver.trigger()) {
				this.receiver.getResult();
			}
		}
		//Write file as job
		
		
	}
}
