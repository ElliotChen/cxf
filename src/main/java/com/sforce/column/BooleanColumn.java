package com.sforce.column;

import org.apache.commons.lang.StringUtils;



public class BooleanColumn extends Column<Boolean> {
	private String yes;
	private String no;
	public BooleanColumn(int index, String name, String sfName) {
		this(index, name, sfName, "", "X");
	}

	public BooleanColumn(int index, String name, String sfName, String yes, String no) {
		super(index, name, sfName);
		this.yes = yes;
		this.no = no;
	}
	
	public Boolean parse(String value) {
		return StringUtils.isNotEmpty(value);
	}

	@Override
	public String format(Object value) {
		if (null == value || !(value instanceof Boolean)) {
			return no;
		}
		
		return (Boolean)value ? yes : no;
	}
}
