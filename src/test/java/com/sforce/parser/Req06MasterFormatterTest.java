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

public class Req06MasterFormatterTest {
	private static final Logger logger = LoggerFactory.getLogger(Req06MasterFormatterTest.class);
	Req06MasterFormatter rf = new Req06MasterFormatter();
	Req06I1AFormatter i1a = new Req06I1AFormatter();
	Req06I1BFormatter i1b = new Req06I1BFormatter();
	Req06I1CFormatter i1c = new Req06I1CFormatter();
	Req06I1DFormatter i1d = new Req06I1DFormatter();
	Req06I1EFormatter i1e = new Req06I1EFormatter();
	Req06I1FFormatter i1f = new Req06I1FFormatter();
	@Test
	public void testInit() {
		
		rf.getSubParsers().add(i1a);
		rf.getSubParsers().add(i1b);
		rf.getSubParsers().add(i1c);
		rf.getSubParsers().add(i1d);
		rf.getSubParsers().add(i1e);
		rf.getSubParsers().add(i1f);
		
		
		rf.listColumnInfo();
	}

	@Test
	public void testParse() {
		try {
			List<String> lines = FileUtils.readLines(new File("/Users/elliot/mqfile/test/req06_sf_light.txt"));
			for (String s: lines) {
				SfSender ss = new SfSender();
				String[] split = ss.split(s, '\t');
				logger.debug("Size is [{}]",split.length);
				if (rf.accept(split)) {
					rf.analysis(split);
				} else if (i1a.accept(split)) {
					i1a.analysis(split);
				} else if (i1b.accept(split)) {
					i1b.analysis(split);
				} else if (i1c.accept(split)) {
					i1c.analysis(split);
				} else if (i1d.accept(split)) {
					i1d.analysis(split);
				} else if (i1e.accept(split)) {
					i1e.analysis(split);
				} else if (i1f.accept(split)) {
					i1f.analysis(split);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
