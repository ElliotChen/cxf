package com.sforce.column;

import java.util.Date;

import com.sforce.util.DateUtils;

public class MonthColumn extends Column<Date> {

	public MonthColumn(int index, String name, String sfName) {
		super(index, name, sfName);
	}

	@Override
	public Date parse(String value) {
		return DateUtils.pareseDate(value);
	}

	@Override
	public String format(Object value) {
		// TODO Auto-generated method stub
		return null;
	}

}
