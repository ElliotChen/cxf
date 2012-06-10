package com.sforce.parser;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.springframework.beans.BeanUtils;

import com.sforce.column.Column;
import com.sforce.column.StringColumn;
import com.sforce.soap.enterprise.sobject.ApplicationC;


public class FormatParser {
	Class clazz = ApplicationC.class;
	List<Column> columns = new ArrayList<Column>();
	
	public void init() {
		columns.add(new StringColumn(0, "applicationIDC", "Application_ID__c"));
		PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(clazz);
		
		for (Column col : columns) {
			for (PropertyDescriptor pd : propertyDescriptors) {
				if (pd.getName().equals(col.getName())) {
					col.setReadMethod(pd.getReadMethod());
					col.setWriteMethod(pd.getWriteMethod());
					break;
				}
			}
		}
	}

	public ApplicationC parse(String[] source) {
		ApplicationC target = new ApplicationC();
		for (Column col : columns) {
			String s = source[col.getIndex()];
			try {
				col.getWriteMethod().invoke(target, col.parse(s));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return target;
	}
	
	
}
