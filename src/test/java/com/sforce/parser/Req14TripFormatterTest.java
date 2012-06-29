package com.sforce.parser;

import static org.junit.Assert.*;

import org.junit.Test;

public class Req14TripFormatterTest {

	@Test
	public void test() {
		Req14TripFormatter rf = new Req14TripFormatter();
		rf.init();
		
		System.out.println(rf.genSQLColumn());
	}

}
