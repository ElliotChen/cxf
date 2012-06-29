package com.sforce.column;

public class DoubleColumn extends Column<Double> {

	public DoubleColumn(int index, String name, String sfName) {
		super(index, name, sfName);
	}

	@Override
	public Double parse(String value) {
		return Double.parseDouble(value);
	}

	@Override
	public String format(Object value) {
		if (null == value) {
			return "";
		}
		return String.valueOf(value);
	}

}
