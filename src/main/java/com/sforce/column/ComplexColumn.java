package com.sforce.column;

import org.apache.commons.lang.StringUtils;



public class ComplexColumn extends Column<String> {
	public ComplexColumn(int index, String name, String sfName) {
		super(index, name, sfName);
	}

	public String parse(String value) {
		return this.removeBrTab(value);
	}

	@Override
	public String format(Object value) {
		if(null == value) {
			return "";
		}
		return removeBrTab(value.toString());
	}
	
	protected String removeBrTab(String value) {
		if (StringUtils.isEmpty(value)) {
			return "";
		}
		String result = value.trim().replaceAll("\\r|\\n", "<br>").replaceAll("\\t", "<tab>");
		return result;
	}
}
