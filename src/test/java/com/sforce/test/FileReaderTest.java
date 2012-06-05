package com.sforce.test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class FileReaderTest {
	@Test
	public void test() {
		try {
			List<String> lines = FileUtils.readLines(new File("/Users/elliot/gitrepo/cxf/src/test/resources/req09.txt"));
			for (String s: lines) {
				System.out.println(s);
				System.out.println(s.split("\\t").length);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
