package com.sforce.to;

import java.util.Date;

public class SfSqlConfig {
	private Date lasySyncDate;
	private String masterId;
	
	public Date getLasySyncDate() {
		return lasySyncDate;
	}
	public void setLasySyncDate(Date lasySyncDate) {
		this.lasySyncDate = lasySyncDate;
	}
	public String getMasterId() {
		return masterId;
	}
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	
}
