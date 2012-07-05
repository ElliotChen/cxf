package com.sforce.parser;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.soap.enterprise.sobject.VisitReportC;
import com.sforce.to.SfSqlConfig;

public class Req15MasterFormatterTest {
	private static final Logger logger = LoggerFactory.getLogger(Req15MasterFormatterTest.class);
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

	@Test
	public void testRead() {
		try {
			List<String> readLines = FileUtils.readLines(new File("/Users/elliot/gitrepo/cxf/src/test/resources/req15.txt"));
			for (String line : readLines) {
				String[] split = StringUtils.splitByWholeSeparatorPreserveAllTokens(line, "\t");
				if (mf.accept(split)) {
					mf.analysis(split);
				} else if (i1f.accept(split)) {
					i1f.analysis(split);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
