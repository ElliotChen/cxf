package com.sforce.parser;


public class StringColumn extends Column<String> {
	public StringColumn(int index, String name, String sfName) {
		super(index, name, sfName);
	}

	public String parse(String value) {
		return value;
	}

	@Override
	public Class getType() {
		// TODO Auto-generated method stub
		return String.class;
	}
}
