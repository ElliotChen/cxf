package com.sforce.parser;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Req14TripFormatterTest {
	private static final Logger logger = LoggerFactory.getLogger(Req14TripFormatterTest.class);
	Req14TripFormatter rf = new Req14TripFormatter();
	Req14VisitFormatter rf2 = new Req14VisitFormatter();
	@Test
	public void test() {
		
		rf.listColumnInfo();
		
		System.out.println(rf.genSQLColumn());
		
		
		
		rf2.listColumnInfo();
		
		System.out.println(rf2.genSQLColumn());
	}

	@Test
	public void testRead() {
		try {
			List<String> readLines = FileUtils.readLines(new File("/Users/elliot/mqfile/test/req14_sf.txt"));
			for (String line : readLines) {
				String[] split = StringUtils.splitByWholeSeparatorPreserveAllTokens(line, "\t");
				logger.debug("Split Size[{}]", split.length);
				if (rf.accept(split)) {
					rf.analysis(split);
				} else if (rf2.accept(split)) {
					rf2.analysis(split);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
