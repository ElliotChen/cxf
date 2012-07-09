package com.sforce.parser;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Req05MasterFormatterTest {
	private static final Logger logger = LoggerFactory.getLogger(Req05MasterFormatterTest.class);
	Req05MasterFormatter mf = new Req05MasterFormatter();
	@Test
	public void testInit() {
		mf.listColumnInfo();
	}

	@Test
	public void testRead() {
		try {
			List<String> readLines = FileUtils.readLines(new File("/Users/elliot/mqfile/test/req05_sf.txt"));
			for (String line : readLines) {
				String[] split = StringUtils.splitByWholeSeparatorPreserveAllTokens(line, "\t");
				logger.debug("Split Size[{}]", split.length);
				if (mf.accept(split)) {
					mf.analysis(split);
				} 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
