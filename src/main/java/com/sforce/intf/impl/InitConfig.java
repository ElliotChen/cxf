package com.sforce.intf.impl;

public class InitConfig {
	private String name;
	private Boolean debugMode;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getDebugMode() {
		return debugMode;
	}
	public void setDebugMode(Boolean debugMode) {
		this.debugMode = debugMode;
	}
}
