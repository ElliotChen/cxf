package com.sforce.parser;

import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.to.SfSqlConfig;


public interface Parser<T extends SObject> {
	void init();
//	String getSyncKey();
	T parse(String[] source);
	
	boolean accept(String[] source);
	
	
	String format(T entity);
	String genSQLColumn();
	String genSfSQL(SfSqlConfig config);
}
