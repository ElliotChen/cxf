package com.sforce.parser;

import java.util.Date;

public class CstDateColumn extends Column<Date> {

	public CstDateColumn(int index, String name, String sfName) {
		super(index, name, sfName);
	}

	@Override
	public Class getType() {
		return Date.class;
	}

	@Override
	public Date parse(String value) {
		return null;
	}

	@Override
	public String format(Object value) {
		// TODO Auto-generated method stub
		return null;
	}

}
