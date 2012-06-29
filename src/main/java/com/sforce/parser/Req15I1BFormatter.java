package com.sforce.parser;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.column.Column;
import com.sforce.column.FakeColumn;
import com.sforce.column.StringColumn;
import com.sforce.soap.enterprise.sobject.OtherRelatedGroupC;
import com.sforce.to.SfSqlConfig;
/**
 *
 */
public class Req15I1BFormatter extends BaseParser<OtherRelatedGroupC> {
	private static final Logger logger = LoggerFactory.getLogger(Req15I1BFormatter.class);
	
	@Override
	public boolean accept(String[] source) {
		return 7 == source.length;
	}

	@Override
	public Logger getLogger() {
		return logger;
	}

	@Override
	protected void initDefaultColumns() {
		this.columns = new ArrayList<Column<?>>();
		int i = 0;
		columns.add(new FakeColumn(i++, "I1B", ""));
		
		columns.add(new StringColumn(i++, "groupNameC", "Group_Name__c"));
		
		this.tableName = "Visit_Report__c.Other_Related_Group__r";
	}

	@Override
	public void buildSyncKey(OtherRelatedGroupC entity) {
	}

	protected String buildSfCondition(SfSqlConfig config) {
		return "";
	}

	@Override
	public void preFormat(OtherRelatedGroupC entity) {
		// TODO Auto-generated method stub
		
	}
	
}
