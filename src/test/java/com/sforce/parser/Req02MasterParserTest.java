package com.sforce.parser;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.soap.enterprise.sobject.SObject;

public class Req02MasterParserTest {
	private static final Logger logger = LoggerFactory.getLogger(Req02MasterParserTest.class);
//	private Req02MasterParser parser = new Req02MasterParser();
	@Test
	public void testInit() {
		Req02MasterParser fp = new Req02MasterParser();
		fp.init();
		logger.debug(fp.genSQLColumn());
	}

	@Test
	public void testParse() {
		try {
//			List<String> lines = FileUtils.readLines(new File("/Users/elliot/gitrepo/cxf/src/test/resources/req02.txt"));
			List<String> lines = FileUtils.readLines(new File("/Users/elliot/gitrepo/cxf/src/test/resources/req02_mxic.txt"));
			for (String s: lines) {
				String[] split = StringUtils.splitByWholeSeparatorPreserveAllTokens(s, "\t");
				logger.debug("Split Size[{}] and source[{}]", split.length, s );
				for (int i=0; i < split.length; i++) {
					logger.debug("{} : [{}]", i+1, split[i]);
				}
				Req02MasterParser fp = new Req02MasterParser();
				fp.init();
				SObject target = fp.parse(split);
				logger.debug("Find Source [{}]",target);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
