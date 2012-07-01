package com.sforce.parser;

import org.junit.Test;

import com.sforce.to.SfSqlConfig;

public class Req15MasterFormatterTest {
	Req15MasterFormatter mf = new Req15MasterFormatter();
	Req15I1AFormatter i1f = new Req15I1AFormatter();
	Req15I1BFormatter i2f = new Req15I1BFormatter();
	Req15I1CFormatter i3f = new Req15I1CFormatter();
	@Test
	public void test() {
		mf.getSubParsers().add(i1f);
		mf.getSubParsers().add(i2f);
		mf.getSubParsers().add(i3f);
		
		mf.listColumnInfo();
		
		
		SfSqlConfig config = new SfSqlConfig();
		System.out.println(mf.genSfSQL(config));
	}

}
