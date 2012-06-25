package com.sforce.parser;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;

import com.sforce.column.Column;
import com.sforce.soap.enterprise.sobject.SObject;

public abstract class BaseParser<T extends SObject> implements Parser<T>{
	protected List<Column> columns = new ArrayList<Column>();
	protected Class domainClass = null;
	private String syncKey;
	
	public BaseParser() {
		this.domainClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public abstract Logger getLogger();
	public abstract void buildSyncKey(T entity);
	public void init() {
		if (columns.isEmpty()) {
			getLogger().warn("Columns is empty, using default columns configuration");
			this.initDefaultColumns();
		}
		PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(domainClass);
		for (Column col : columns) {
			for (PropertyDescriptor pd : propertyDescriptors) {
				if (pd.getName().equals(col.getName())) {
					col.setReadMethod(pd.getReadMethod());
					col.setWriteMethod(pd.getWriteMethod());
					if (null == col.getReadMethod() && Boolean.class.equals(pd.getPropertyType())) {
						Method method = null;
						try {
							method = domainClass.getMethod("is"+pd.getName(), null);
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (null != method) {
							col.setReadMethod(method);
						} else {
							getLogger().error("class is {}, can't find [{}]",pd.getPropertyType(),"is"+pd.getName());
						}
					}
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
	
	public T parse(String[] source) {
		Object target = null;
		try {
			target = this.domainClass.newInstance();
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		for (Column col : columns) {
			String s = source[col.getIndex()];
			try {
				if (StringUtils.isNotEmpty(s) && null != col.getWriteMethod()) {
					col.getWriteMethod().invoke(target, col.parse(s));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		this.buildSyncKey((T)target);
		return (T)target;
	}
	
	//@Override
		public String getSyncKey() {
			return this.syncKey;
		}

		public void setSyncKey(String syncKey) {
			this.syncKey = syncKey;
		}
}
