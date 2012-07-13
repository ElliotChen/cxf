package com.sforce.column;

import org.apache.commons.lang.StringUtils;



public class StringColumn extends Column<String> {
	public StringColumn(int index, String name, String sfName) {
		super(index, name, sfName);
	}

	public StringColumn(int index, String name, String sfName, String sfNullName) {
		super(index, name, sfName);
		
		this.sfNullName = sfNullName;
	}
	public String parse(String value) {
		if (StringUtils.isEmpty(value)) {
			return "";
		}
		return value.trim();
	}

	@Override
	public String format(Object value) {
		return value == null ? "" : value.toString().trim();
	}
}
