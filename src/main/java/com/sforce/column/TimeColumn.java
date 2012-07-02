package com.sforce.column;

import java.util.Date;

import com.sforce.util.DateUtils;

public class TimeColumn extends Column<Date> {

	public TimeColumn(int index, String name, String sfName) {
		super(index, name, sfName);
	}

	@Override
	public Date parse(String value) {
		return DateUtils.pareseTime(value);
	}

	@Override
	public String format(Object value) {
		if (null == value || !(value instanceof Date)) {
			return "";
		}
		return DateUtils.formatTime((Date) value);
	}

}
