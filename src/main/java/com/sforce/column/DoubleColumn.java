package com.sforce.column;

import org.apache.commons.lang.StringUtils;

public class DoubleColumn extends Column<Double> {
	private String format = "%1$.4f";
	public DoubleColumn(int index, String name, String sfName) {
		super(index, name, sfName);
//		if (StringUtils)
//		this.format = format;
	}

	@Override
	public Double parse(String value) {
		if (StringUtils.isEmpty(value)) {
			return null;
		}
		String svalue = value.trim();
		return Double.parseDouble(svalue);
	}

	@Override
	public String format(Object value) {
		if (null == value || !(value instanceof Double)) {
			return "";
		}
		
		return String.format(format, value);
	}

}
