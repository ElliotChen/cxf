package com.sforce.column;

import static org.junit.Assert.*;

import org.junit.Test;

public class DoubleColumnTest {

	@Test
	public void test() {
		DoubleColumn dc = new DoubleColumn(1, "", "");
		String format = dc.format(new Double(99999999999999.91));
		System.out.println(format);
	}

}
