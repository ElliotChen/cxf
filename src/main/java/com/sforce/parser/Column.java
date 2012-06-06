package com.sforce.parser;

import java.lang.reflect.Method;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public abstract class Column<T> {
	public static final QName qname = new QName(
			"urn:sobject.enterprise.soap.sforce.com");
	protected int index;
	protected String name;
	protected String sfName;

	protected Method readMethod;
	protected Method writeMethod;

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

	public abstract Class getType();
	public abstract T parse(String value);
}
