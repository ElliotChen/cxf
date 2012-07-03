package com.sforce.parser;

import static org.junit.Assert.*;

import org.junit.Test;

public class Req11MasterFormatterTest {
	
	@Test
	public void testInit() {
		Req11MasterFormatter rf = new Req11MasterFormatter();
		rf.listColumnInfo();
		
		System.out.println(rf.genSQLColumn());
		
	}

}
