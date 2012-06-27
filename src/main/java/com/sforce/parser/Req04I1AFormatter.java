package com.sforce.parser;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.column.Column;
import com.sforce.column.FakeColumn;
import com.sforce.column.StringColumn;
import com.sforce.soap.enterprise.sobject.CompetitorPriceItemC;
import com.sforce.soap.enterprise.sobject.Opportunity;
import com.sforce.to.SfSqlConfig;
import com.sforce.util.DateUtils;
/**
 * @author elliot
 *
 */
public class Req04I1AFormatter extends BaseParser<CompetitorPriceItemC> {
	private static final Logger logger = LoggerFactory.getLogger(Req04I1AFormatter.class);
	
	@Override
	public boolean accept(String[] source) {
		return 2 == source.length;
	}

	@Override
	public Logger getLogger() {
		return logger;
	}

	@Override
	protected void initDefaultColumns() {
		this.columns = new ArrayList<Column<?>>();
		int i = 0;
		columns.add(new FakeColumn(i++, "I1A", ""));
		columns.add(new StringColumn(i++, "competitorPriceC", "Competitor_Price__c"));
		columns.add(new StringColumn(i++, "name", "Name"));
		
		this.tableName = "Competitor_price__c.Competitor_Price_Item__r";
	}

	@Override
	public void buildSyncKey(CompetitorPriceItemC entity) {
	}

	protected String buildSfCondition(SfSqlConfig config) {
		return "";
	}

	@Override
	public String genSfSQL(SfSqlConfig config) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(genSQLColumn());
		sb.append(" FROM "+tableName);
		return sb.toString();
	}
	
	
}
