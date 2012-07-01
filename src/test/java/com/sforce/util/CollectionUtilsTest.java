package com.sforce.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CollectionUtilsTest {

	@Test
	public void test() {
		int s = 301;
		List<String> source = new ArrayList<String>(s);
		
		for (int i = 0; i< s; i++) {
			source.add("");
		}
		System.out.println(source.size());
		List<List<String>> splitList = CollectionUtils.splitList(source, 200);
		for (List<String> target : splitList) {
			System.out.println(target.size());
		}
	}

}
