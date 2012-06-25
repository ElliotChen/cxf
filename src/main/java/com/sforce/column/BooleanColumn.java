package com.sforce.column;

import org.apache.commons.lang.StringUtils;



public class BooleanColumn extends Column<Boolean> {
	public BooleanColumn(int index, String name, String sfName) {
		super(index, name, sfName);
	}

	public Boolean parse(String value) {
		return StringUtils.isNotEmpty(value);
		//return "YES".equalsIgnoreCase(value) || "Y".equalsIgnoreCase(value) || "TRUE".equalsIgnoreCase(value);
	}

	@Override
	public String format(Object value) {
		return value == null ? "" : value.toString();
	}
}
