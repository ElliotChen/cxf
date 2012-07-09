package com.sforce.parser;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.intf.impl.SfSender;
import com.sforce.soap.enterprise.sobject.SObject;

public class Req11MasterFormatterTest {
	private static final Logger logger = LoggerFactory.getLogger(Req11MasterFormatterTest.class);
	@Test
	public void testInit() {
		Req11MasterFormatter rf = new Req11MasterFormatter();
		rf.listColumnInfo();
		
		System.out.println(rf.genSQLColumn());
		
	}

	@Test
	public void testParse() {
		try {
			List<String> lines = FileUtils.readLines(new File("/Users/elliot/mqfile/test/req11_sf.txt"));
			for (String s: lines) {
				SfSender ss = new SfSender();
				String[] split = ss.split(s, '\t');
				logger.debug("Size is [{}]",split.length);
				Req11MasterFormatter fp = new Req11MasterFormatter();
				fp.init();
				fp.analysis(split);
				SObject target = fp.parse(split);
				logger.debug("Find Source [{}]",target);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
