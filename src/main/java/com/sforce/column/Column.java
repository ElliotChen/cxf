package com.sforce.column;

import java.lang.reflect.Method;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public abstract class Column<T> {
	public static final QName qname = new QName("urn:sobject.enterprise.soap.sforce.com");
	public static final char DEFAULT_SEPARATOR = '\t';
	protected int index;
	protected String name;
	protected String sfName;
	protected char separator;
	protected Method readMethod;
	protected Method writeMethod;

	protected Boolean fake = Boolean.FALSE;
	
	protected Boolean nullable = Boolean.TRUE;
	public Column(int index, String name, String sfName) {
		super();
		this.index = index;
		this.name = name;
		this.sfName = sfName;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSfName() {
		return sfName;
	}

	public void setSfName(String sfName) {
		this.sfName = sfName;
	}

	public Method getReadMethod() {
		return readMethod;
	}

	public void setReadMethod(Method readMethod) {
		this.readMethod = readMethod;
	}

	public Method getWriteMethod() {
		return writeMethod;
	}

	public void setWriteMethod(Method writeMethod) {
		this.writeMethod = writeMethod;
	}

	public Boolean getFake() {
		return fake;
	}

	public void setFake(Boolean fake) {
		this.fake = fake;
	}
	
	public char getSeparator() {
		return separator;
	}

	public void setSeparator(char separator) {
		this.separator = separator;
	}

	public Boolean getNullable() {
		return nullable;
	}

	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}

	public abstract T parse(String value);
	public abstract String format(Object value);
}
