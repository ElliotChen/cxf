package com.sforce.parser;

import java.util.List;

import com.sforce.soap.enterprise.sobject.ApplicationC;

public interface Parser<T> {
	void init();
	T parse(String[] source);
	boolean accept(String[] source);
	String genSQLColumn();
}
