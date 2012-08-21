package com.sforce.parser;

import org.apache.commons.lang.StringUtils;

import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.to.SfSqlConfig;

public abstract class SubParser<T extends SObject, Master extends SObject> extends BaseParser<T> {
	public abstract void preFormat(Master master, T entity);
	
	protected String wcondition;
	
	@Override
	public String genSfSQL(SfSqlConfig config) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(genSQLColumn());
		sb.append(" FROM "+tableName);
		if (StringUtils.isNotEmpty(wcondition)) {
			sb.append(" WHERE "+wcondition);
		}
		return sb.toString();
	}

	public String getWcondition() {
		return wcondition;
	}

	public void setWcondition(String wcondition) {
		this.wcondition = wcondition;
	}
	
}
