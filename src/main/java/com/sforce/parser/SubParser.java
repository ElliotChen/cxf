package com.sforce.parser;

import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.to.SfSqlConfig;

public abstract class SubParser<T extends SObject, Master extends SObject> extends BaseParser<T> {
	public abstract void preFormat(Master master, T entity);
	
	@Override
	public String genSfSQL(SfSqlConfig config) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(genSQLColumn());
		sb.append(" FROM "+tableName);
		return sb.toString();
	}
}
