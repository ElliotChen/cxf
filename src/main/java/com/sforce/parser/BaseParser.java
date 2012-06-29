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
import com.sforce.to.SfSqlConfig;

public abstract class BaseParser<T extends SObject> implements Parser<T> {
	protected List<Column<?>> columns = new ArrayList<Column<?>>();
	protected Class domainClass = null;
	protected String syncKey;
	protected String tableName;
	
	protected List<Parser<?>> subParsers = new ArrayList<Parser<?>>();
	public BaseParser() {
		this.domainClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public abstract Logger getLogger();
	protected abstract void initDefaultColumns();
	public abstract void buildSyncKey(T entity);
	public abstract void preFormat(T entity);
	public void init() {
		if (columns.isEmpty()) {
			getLogger().info("Columns is empty, using default configuration.");
			this.initDefaultColumns();
		}
		PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(domainClass);
		for (Column<?> col : columns) {
			if (col.getFake()) {
				continue;
			}
			for (PropertyDescriptor pd : propertyDescriptors) {
				if (pd.getName().equals(col.getName())) {
					col.setReadMethod(pd.getReadMethod());
					col.setWriteMethod(pd.getWriteMethod());
					if (null == col.getReadMethod() && Boolean.class.equals(pd.getPropertyType())) {
						Method method = null;
						try {
							method = domainClass.getMethod("is"+pd.getName(), null);
						} catch (Exception e) {
							getLogger().error("Get Method is{} of {} failed", pd.getName(), this.domainClass);
						}
						if (null != method) {
							col.setReadMethod(method);
						} else {
							getLogger().error("class {}, can't find [{}]",pd.getPropertyType(),"is"+pd.getName());
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
	
	
	@Override
	public String genSQLColumn() {
		StringBuilder sb = new StringBuilder();
//		Column<?> col = null;
		if (!columns.isEmpty()) {
			for (Column<?> col : columns) {
				if (col.getFake()) {
					continue;
				}
				if(StringUtils.isNotEmpty(col.getSfName())) {
					sb.append(col.getSfName());
					sb.append(",");
				}
			}
			/*
			col = columns.get(0);
			sb.append(col.getSfName());
			
			for (int i = 1; i < columns.size(); i++) {
				col = columns.get(i);
				if (col.getFake()) {
					continue;
				}
				
				sb.append(",");
				sb.append(col.getSfName());
			}
			*/
		}
		
		String cols = sb.toString();
		if (cols.endsWith(",")) {
			cols = cols.substring(0, cols.length()-1);
		}
		return cols;
	}
	
	@Override
	public String genSfSQL(SfSqlConfig config) {
		if (StringUtils.isEmpty(tableName)) {
			this.getLogger().error("Table Name can't be empty value for Formatter.");
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(genSQLColumn());
		if (null != this.subParsers) {
			for (Parser<?> p : subParsers) {
				sb.append(", ("+p.genSfSQL(config)+") ");
			}
		}
		sb.append(" FROM "+tableName);
		sb.append(" WHERE Id <> null ");
		sb.append(this.buildSfCondition(config));
		return sb.toString();
	}
	
	protected String buildSfCondition(SfSqlConfig config) {
		return "";
	}
	
	@Override
	public String format(T entity) {
		StringBuilder sb = new StringBuilder();
		String column = "";
		boolean first = true;
		for (Column<?> col : columns) {
			if (first) {
				first = false;
			} else {
				sb.append('\t');
			}
			if (col.getFake()) {
				sb.append(col.getName());
			} else {
				try {
					Object result = col.getReadMethod().invoke(entity, null);
					column = col.format(col.getReadMethod().invoke(entity, null));
					sb.append(column);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		sb.append('\n');
		return sb.toString();
	}
	
	public T parse(String[] source) {
		Object target = null;
		try {
			target = this.domainClass.newInstance();
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		for (Column<?> col : columns) {
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

	public String getSyncKey() {
		return this.syncKey;
	}

	public void setSyncKey(String syncKey) {
		this.syncKey = syncKey;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public List<Parser<?>> getSubParsers() {
		return subParsers;
	}
	public void setSubParsers(List<Parser<?>> subParsers) {
		this.subParsers = subParsers;
	}
}
