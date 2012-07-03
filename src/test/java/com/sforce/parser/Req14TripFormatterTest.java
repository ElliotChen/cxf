package com.sforce.parser;

import static org.junit.Assert.*;

import org.junit.Test;

public class Req14TripFormatterTest {

	@Test
	public void test() {
		Req14TripFormatter rf = new Req14TripFormatter();
		rf.listColumnInfo();
		
		System.out.println(rf.genSQLColumn());
		
		
		Req14VisitFormatter rf2 = new Req14VisitFormatter();
		rf2.listColumnInfo();
		
		System.out.println(rf2.genSQLColumn());
	}

}
