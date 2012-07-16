package com.sforce.column;

import org.apache.commons.lang.StringUtils;

public class DoubleColumn extends Column<Double> {
	public static final String DEFAULT_FORMAT= "%1$.4f";
	private String format = DEFAULT_FORMAT;
	public DoubleColumn(int index, String name, String sfName) {
		this(index, name, sfName, DEFAULT_FORMAT);
	}
	
	public DoubleColumn(int index, String name, String sfName, String format) {
		super(index, name, sfName);
		this.format = format;
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
