package com.sforce.column;



public class StringColumn extends Column<String> {
	public StringColumn(int index, String name, String sfName) {
		super(index, name, sfName);
	}

	public String parse(String value) {
		return value;
	}

	@Override
	public String format(Object value) {
		return value == null ? "" : value.toString();
	}
}
