package com.sforce.parser;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;

import com.sforce.soap.enterprise.sobject.ApplicationC;

public abstract class BaseParser<T> implements Parser<T>{
	List<Column> columns = new ArrayList<Column>();
	public abstract Logger getLogger();
	
	public void init() {
		if (columns.isEmpty()) {
			getLogger().warn("Columns is empty, using default columns configuration");
			this.initDefaultColumns();
		}
		PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(ApplicationC.class);

		for (Column col : columns) {
			for (PropertyDescriptor pd : propertyDescriptors) {
				if (pd.getName().equals(col.getName())) {
					col.setReadMethod(pd.getReadMethod());
					col.setWriteMethod(pd.getWriteMethod());
					break;
				}
			}
			if (null == col.getReadMethod()) {
				getLogger().error("Can't find ReadMethod for col[{}]", col.getName());
			}
			if (null == col.getWriteMethod()) {
				getLogger().error("Can't find WriteMethod for col[{}]", col.getName());
			}
		}
	}
	protected abstract void initDefaultColumns();
	
	
	public String genSQLColumn() {
		StringBuilder sb = new StringBuilder();
		for (Column col : columns) {
			sb.append(col.getSfName()+",");
		}
		return sb.toString();
	}
	
	public String format(T entity) {
		StringBuilder sb = new StringBuilder();
		String column = "";
		for (Column col : columns) {
			try {
				Object result = col.getReadMethod().invoke(entity, null);
				column = col.format(col.getReadMethod().invoke(entity, null));
				sb.append(column+"\t");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
