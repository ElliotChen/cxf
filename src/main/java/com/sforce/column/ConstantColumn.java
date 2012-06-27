package com.sforce.column;



public class ConstantColumn extends Column<String> {
	private String constant;
	public ConstantColumn(int index, String name, String sfName, String constant) {
		super(index, name, sfName);
		this.fake = Boolean.TRUE;
		this.constant = constant;
	}

	public String parse(String value) {
		return value;
	}

	@Override
	public String format(Object value) {
		return value == null ? "" : value.toString();
	}
}
