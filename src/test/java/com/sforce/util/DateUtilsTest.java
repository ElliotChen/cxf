package com.sforce.util;

import java.util.Date;

import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void testSfDateTiem() {
		System.out.println(DateUtils.formatSfDateTime(new Date()));
	}

}
