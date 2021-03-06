package com.sforce.test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.parser.Req09MasterParser;
import com.sforce.soap.enterprise.sobject.ApplicationC;

public class FileReaderTest {
	private static final Logger logger = LoggerFactory.getLogger(FileReaderTest.class);
	@Test
	public void test() {
		try {
			List<String> lines = FileUtils.readLines(new File("/Users/elliot/gitrepo/cxf/src/test/resources/req09.txt"));
			for (String s: lines) {
				System.out.println(s);
				String[] split = s.split("\\t");
				
				Req09MasterParser fp = new Req09MasterParser();
				fp.init();
				ApplicationC target = fp.parse(split);
				logger.debug("Find Source [{}]",target);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFormat() {
		ApplicationC entity = new ApplicationC();
		entity.setApplicationIDC("idc");
		
		Req09MasterParser fp = new Req09MasterParser();
		fp.init();
		String format = fp.format(entity);
		logger.debug("Format application to [{}]", format);
		logger.debug("SELECT Columns is [{}]", fp.genSQLColumn());
	}
}
