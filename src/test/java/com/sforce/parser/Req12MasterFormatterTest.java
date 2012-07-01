package com.sforce.parser;

import static org.junit.Assert.*;

import org.junit.Test;

public class Req12MasterFormatterTest {

	@Test
	public void test() {
		Req12MasterFormatter rf = new Req12MasterFormatter();
		rf.listColumnInfo();
		
		System.out.println(rf.genSQLColumn());
	}

}
