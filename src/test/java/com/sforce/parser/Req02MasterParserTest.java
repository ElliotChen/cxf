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
	Req02MasterParser fp = new Req02MasterParser();
	@Test
	public void testInit() {
		fp.listColumnInfo();
		logger.debug(fp.genSQLColumn());
	}

	@Test
	public void testParse() {
		try {
//			List<String> lines = FileUtils.readLines(new File("/Users/elliot/gitrepo/cxf/src/test/resources/req02.txt"));
			List<String> lines = FileUtils.readLines(new File("/Users/elliot/mqfile/test/req02_mxic_light.txt"));
			for (String s: lines) {
				String[] split = StringUtils.splitByWholeSeparatorPreserveAllTokens(s, "\t");
				fp.analysis(split);
				SObject target = fp.parse(split);
				logger.debug("Find Source [{}]",target);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
