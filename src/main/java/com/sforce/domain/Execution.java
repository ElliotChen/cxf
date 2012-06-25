package com.sforce.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Execution extends AbstractStrOidAuditable {
	
	private static final long serialVersionUID = 2375006374195508480L;

	@Column(name = "COMPONENT", length = 50, unique=true)
	private String component;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_SUCCESS_DATE")
	private Date lastSuccessDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS", length = 20)
	private ExecutionStatus status;

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public Date getLastSuccessDate() {
		return lastSuccessDate;
	}

	public void setLastSuccessDate(Date lastSuccessDate) {
		this.lastSuccessDate = lastSuccessDate;
	}

	public ExecutionStatus getStatus() {
		return status;
	}

	public void setStatus(ExecutionStatus status) {
		this.status = status;
	}
	
}
