package com.sforce.parser;

import com.sforce.soap.enterprise.sobject.SObject;


public interface Parser<T extends SObject> {
	void init();
//	String getSyncKey();
	T parse(String[] source);
	boolean accept(String[] source);
	String genSQLColumn();
}
