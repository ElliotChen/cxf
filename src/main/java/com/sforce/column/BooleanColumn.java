package com.sforce.column;




public class BooleanColumn extends Column<Boolean> {
	private String yes;
	private String no;
	public BooleanColumn(int index, String name, String sfName) {
		this(index, name, sfName, "X", "");
	}

	public BooleanColumn(int index, String name, String sfName, String yes, String no) {
		super(index, name, sfName);
		this.yes = yes;
		this.no = no;
		this.nullable = Boolean.FALSE;
	}
	
	public Boolean parse(String value) {
		if (yes.equals(value)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public String format(Object value) {
		if (null == value || !(value instanceof Boolean)) {
			return no;
		}
		
		return (Boolean)value ? yes : no;
	}
}
