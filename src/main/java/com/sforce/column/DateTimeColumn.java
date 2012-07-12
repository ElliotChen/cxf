package com.sforce.column;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.sforce.util.DateUtils;

public class DateTimeColumn extends Column<Date> {

	public DateTimeColumn(int index, String name, String sfName) {
		super(index, name, sfName);
	}

	@Override
	public Date parse(String value) {
		if (StringUtils.isEmpty(value)) {
			return null;
		}
		return DateUtils.pareseDateTime(value);
	}

	@Override
	public String format(Object value) {
		if (null == value || !(value instanceof Date)) {
			return "";
		}
		return DateUtils.formatDateTime((Date) value);
	}

}
