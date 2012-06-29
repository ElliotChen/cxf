package com.sforce.column;



public class OgnlColumn extends Column<String> {
	public OgnlColumn(int index, String name, String sfName) {
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
