package com.sforce.parser;

import org.junit.Test;

public class Req06MasterFormatterTest {
	Req06MasterFormatter rf = new Req06MasterFormatter();
	Req06I1AFormatter i1a = new Req06I1AFormatter();
//	Req06I1BFormatter i1b = new Req06I1BFormatter();
	Req06I1CFormatter i1c = new Req06I1CFormatter();
	Req06I1DFormatter i1d = new Req06I1DFormatter();
	Req06I1EFormatter i1e = new Req06I1EFormatter();
	Req06I1FFormatter i1f = new Req06I1FFormatter();
	@Test
	public void testInit() {
		
		rf.listColumnInfo();
		
		rf.getSubParsers().add(i1a);
		rf.getSubParsers().add(i1c);
		rf.getSubParsers().add(i1d);
		rf.getSubParsers().add(i1e);
		rf.getSubParsers().add(i1f);
		
		
		rf.listColumnInfo();
	}

}
