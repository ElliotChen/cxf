package com.sforce.parser;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.column.Column;
import com.sforce.column.DoubleColumn;
import com.sforce.column.FakeColumn;
import com.sforce.column.MonthColumn;
import com.sforce.column.StringColumn;
import com.sforce.soap.enterprise.sobject.CompetitorPriceItemC;
import com.sforce.to.SfSqlConfig;
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
		columns.add(new StringColumn(i++, "competitorNameC", "Competitor_Name__c"));
		columns.add(new StringColumn(i++, "currencyC", "Currency__c"));
		columns.add(new DoubleColumn(i++, "exchangeRateUSDC", "Exchange_Rate_USD__c"));
		columns.add(new DoubleColumn(i++, "exchangeRateJPYC", "Exchange_Rate_JPY__c"));
		columns.add(new DoubleColumn(i++, "exchangeRateJPYUSDC", "Exchange_Rate_JPY_USD__c"));
		columns.add(new MonthColumn(i++, "month1C", "Month1__c"));
		columns.add(new DoubleColumn(i++, "USASP1C", "US_ASP1__c"));
		columns.add(new DoubleColumn(i++, "endPrice1C", "End_Price_1__c"));
		columns.add(new MonthColumn(i++, "month2C", "Month2__c"));
		columns.add(new DoubleColumn(i++, "USASP2C", "US_ASP2__c"));
		columns.add(new DoubleColumn(i++, "endPrice2C", "End_Price_2__c"));
		columns.add(new MonthColumn(i++, "month3C", "Month3__c"));
		columns.add(new DoubleColumn(i++, "USASP3C", "US_ASP3__c"));
		columns.add(new DoubleColumn(i++, "endPrice3C", "End_Price_3__c"));
		columns.add(new MonthColumn(i++, "month4C", "Month4__c"));
		columns.add(new DoubleColumn(i++, "USASP4C", "US_ASP4__c"));
		columns.add(new DoubleColumn(i++, "endPrice4C", "End_Price_4__c"));
		columns.add(new MonthColumn(i++, "month5C", "Month5__c"));
		columns.add(new DoubleColumn(i++, "USASP5C", "US_ASP5__c"));
		columns.add(new DoubleColumn(i++, "endPrice5C", "End_Price_5__c"));
		columns.add(new MonthColumn(i++, "month6C", "Month6__c"));
		columns.add(new DoubleColumn(i++, "USASP6C", "US_ASP6__c"));
		columns.add(new DoubleColumn(i++, "endPrice6C", "End_Price_6__c"));
		
		
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

	@Override
	public void preFormat(CompetitorPriceItemC entity) {
		// TODO Auto-generated method stub
		
	}
	
	
}
