package com.sforce.parser;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Req02MasterParserTest {
	private static final Logger logger = LoggerFactory.getLogger(Req02MasterParserTest.class);
	@Test
	public void test() {
		Req02MasterParser fp = new Req02MasterParser();
		fp.init();
		logger.debug(fp.genSQLColumn());
	}

}
