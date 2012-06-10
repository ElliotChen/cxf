package com.sforce.parser;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.soap.enterprise.sobject.ExchangeRateC;

public class Req01MasterParserTest {
	private static final Logger logger = LoggerFactory.getLogger(Req01MasterParserTest.class);
	@Test
	public void testParse() {
		try {
			List<String> lines = FileUtils.readLines(new File("/Users/elliot/gitrepo/cxf/src/test/resources/req01.txt"));
			for (String s: lines) {
				System.out.println(s);
				String[] split = s.split("\\t");
				
				Req01MasterParser fp = new Req01MasterParser();
				fp.init();
				ExchangeRateC target = fp.parse(split);
				logger.debug("Find Source [{}]",target);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGenSQL() {
		Req01MasterParser fp = new Req01MasterParser();
		fp.init();
		logger.debug(fp.genSQLColumn());
	}

}
