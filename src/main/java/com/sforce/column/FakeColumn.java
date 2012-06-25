package com.sforce.column;

public class FakeColumn extends Column<String> {
	public FakeColumn(int index, String name, String sfName) {
		super(index, name, sfName);
		this.fake = Boolean.TRUE;
	}

	public String parse(String value) {
		return "";
	}

	@Override
	public String format(Object value) {
		return "";
	}
}
