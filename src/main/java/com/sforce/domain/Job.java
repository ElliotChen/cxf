package com.sforce.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Job extends AbstractStrOidAuditable {

	private static final long serialVersionUID = 3101804189194034606L;

	@Column(name = "COMPONENT", length = 50)
	private String component;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "STATE", length = 20)
	private JobState state;
	
	@Column(name = "MQ_ID", length = 50)
	private String mqId;
	
	@Column(name = "MQ_BASE64_ID", length = 100)
	private String mqBase64Id;
	
	@Column(name="LOCATION", length=400)
	private String location;

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public JobState getState() {
		return state;
	}

	public void setState(JobState state) {
		this.state = state;
	}

	public String getMqId() {
		return mqId;
	}

	public void setMqId(String mqId) {
		this.mqId = mqId;
	}

	public String getMqBase64Id() {
		return mqBase64Id;
	}

	public void setMqBase64Id(String mqBase64Id) {
		this.mqBase64Id = mqBase64Id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
