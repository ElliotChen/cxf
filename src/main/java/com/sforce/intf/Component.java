package com.sforce.intf;

public interface Component {
	Status getStatus();
	Boolean init();
	Boolean trigger();
	
	Boolean isAvailable();
}
