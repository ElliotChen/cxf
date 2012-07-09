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
import com.sforce.to.SfSqlConfig;

public class Req04MasterFormatterTest {
	private static final Logger logger = LoggerFactory.getLogger(Req04MasterFormatterTest.class);
	Req04MasterFormatter mf = new Req04MasterFormatter();
	Req04I1AFormatter i1af = new Req04I1AFormatter();
	@Test
	public void testInit() {
		mf.init();
		i1af.init();
		
		mf.listColumnInfo();
		i1af.listColumnInfo();
		
		
		SfSqlConfig config = new SfSqlConfig();
		mf.getSubParsers().add(i1af);
		logger.info(mf.genSfSQL(config));
	}

	@Test
	public void testParse() {
		try {
			List<String> lines = FileUtils.readLines(new File("/Users/elliot/mqfile/test/req04_sf.txt"));
			for (String s: lines) {
				SfSender ss = new SfSender();
				String[] split = ss.split(s, '\t');
				logger.debug("Size is [{}]",split.length);
				Req04MasterFormatter fp = new Req04MasterFormatter();
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
