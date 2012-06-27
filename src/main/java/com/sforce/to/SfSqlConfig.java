package com.sforce.to;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sforce.parser.Parser;

public class SfSqlConfig {
	private Date lasySyncDate;
	private String masterId;
	private List<Parser<?>> subParsers = new ArrayList<Parser<?>>();
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
	public List<Parser<?>> getSubParsers() {
		return subParsers;
	}
	public void setSubParsers(List<Parser<?>> subParsers) {
		this.subParsers = subParsers;
	}
	
}
