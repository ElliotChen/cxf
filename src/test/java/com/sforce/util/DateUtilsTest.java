package com.sforce.util;

import java.text.DecimalFormat;
import java.util.Date;

import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void testSfDateTiem() {
		System.out.println(DateUtils.formatSfDateTime(new Date()));
	}

	@Test
	public void testNumber() {
		Double value = new Double(123.05d);
		DecimalFormat df = new DecimalFormat("000 ###.####");
		System.out.println(df.format(value));
	}
}
