package com.sforce.intf;

public interface Component extends Runnable {
	Status getStatus();
	Boolean init();
//	Boolean trigger();
	
	Boolean isAvailable();
}
