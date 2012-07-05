package com.sforce.column;

import java.text.NumberFormat;

import org.springframework.format.number.NumberFormatter;

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
