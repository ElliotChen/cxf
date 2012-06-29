package com.sforce.parser;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.to.SfSqlConfig;

public class Req04MasterFormatterTest {
	private static final Logger logger = LoggerFactory.getLogger(Req04MasterFormatterTest.class);
	Req04MasterFormatter mf = new Req04MasterFormatter();
	Req04I1AFormatter i1af = new Req04I1AFormatter();
	@Test
	public void test() {
		mf.init();
		i1af.init();
		
		SfSqlConfig config = new SfSqlConfig();
		mf.getSubParsers().add(i1af);
		logger.info(mf.genSfSQL(config));
	}

}
