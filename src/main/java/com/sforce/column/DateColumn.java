package com.sforce.column;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.sforce.util.DateUtils;

public class DateColumn extends Column<Date> {
	public static final String NULL_DATE = "00000000";
	public DateColumn(int index, String name, String sfName) {
		super(index, name, sfName);
	}

	@Override
	public Date parse(String value) {
		if (StringUtils.isEmpty(value) || NULL_DATE.equals(value)) {
			return null;
		}
		return DateUtils.pareseDate(value);
	}

	@Override
	public String format(Object value) {
		if (null == value || !(value instanceof Date)) {
			return "";
		}
		return DateUtils.formatDate((Date) value);
	}
	
	@Override
	public boolean checkIsNull(String source) {
		return StringUtils.isEmpty(source) || NULL_DATE.equals(source);
	}
}
