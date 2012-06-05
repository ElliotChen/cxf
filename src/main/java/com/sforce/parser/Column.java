package com.sforce.parser;

import java.lang.reflect.Method;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class Column {
	private static final QName qname = new QName("urn:sobject.enterprise.soap.sforce.com");
	private int index;
	private String name;
	private String sfName;
	
	private Class type;
	private Method readMethod;
	private Method writeMethod;
	
	public Column(int index, String name, String sfName, Class type) {
		super();
		this.index = index;
		this.name = name;
		this.sfName = sfName;
		this.type = type;
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
	
	public Class getType() {
		return type;
	}

	public void setType(Class type) {
		this.type = type;
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
	
	public JAXBElement parse(String value) {
		JAXBElement element = new JAXBElement<String>(qname, this.type, value);
		return element;
	}
}
