package com.sforce.parser;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class Req14TripFormatterTest {
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
			List<String> readLines = FileUtils.readLines(new File("/Users/elliot/gitrepo/cxf/src/test/resources/req14.txt"));
			for (String line : readLines) {
				String[] split = StringUtils.splitByWholeSeparatorPreserveAllTokens(line, "\t");
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
