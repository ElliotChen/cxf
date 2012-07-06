package com.sforce.parser;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.column.Column;
import com.sforce.column.DateColumn;
import com.sforce.column.DoubleColumn;
import com.sforce.column.FakeColumn;
import com.sforce.column.StringColumn;
import com.sforce.soap.enterprise.sobject.Opportunity;
import com.sforce.soap.enterprise.sobject.OpportunityDataC;
import com.sforce.soap.enterprise.sobject.ProductOpportunityC;
import com.sforce.to.SfSqlConfig;
/**
 * 
 * @author elliot
 *
 */
public class Req06I1FFormatter extends SubParser<OpportunityDataC, Opportunity> {
	private static final Logger logger = LoggerFactory.getLogger(Req06I1FFormatter.class);
	
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
		columns.add(new FakeColumn(i++, "I1F", ""));
		//43,3,2,5,5,36,10
				for (int index = 0; index < 43; index++) {
					columns.add(new FakeColumn(i++, "", ""));
				}
				for (int index = 0; index < 3; index++) {
					columns.add(new FakeColumn(i++, "", ""));
				}
				for (int index = 0; index < 2; index++) {
					columns.add(new FakeColumn(i++, "", ""));
				}
				for (int index = 0; index < 5; index++) {
					columns.add(new FakeColumn(i++, "", ""));
				}
				for (int index = 0; index < 5; index++) {
					columns.add(new FakeColumn(i++, "", ""));
				}
				for (int index = 0; index < 36; index++) {
					columns.add(new FakeColumn(i++, "", ""));
				}
		columns.add(new StringColumn(i++, "id", "")); //cheat
		columns.add(new StringColumn(i++, "EPNNameC", "EPN_Name__c"));
		columns.add(new DateColumn(i++, "startDateC", "Start_Date__c"));
		columns.add(new StringColumn(i++, "periodTypeC", "Period_Type__c"));
		columns.add(new DoubleColumn(i++, "monthQtyC", "Month_Qty__c"));
		columns.add(new StringColumn(i++, "currencyC", "Currency__c"));
		columns.add(new DoubleColumn(i++, "quotePriceC", "Quote_Price__c"));
		columns.add(new DoubleColumn(i++, "quotePriceUSDC", "Quote_Price_USD__c"));
		columns.add(new DoubleColumn(i++, "SAMQtyKeaC", "SAM_Qty_Kea__c"));
		columns.add(new DoubleColumn(i++, "SOMQtyKeaC", "SOM_Qty_Kea__c"));
		
//		this.tableName = "Product_Opportunity__c.Opportunity_Data__r";
		this.tableName = "Opportunity_Data__c";
	}

	@Override
	public void postParse(OpportunityDataC entity) {
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
		sb.append(" WHERE Opportunity_Data__c.Product_Opportunity__r.DI__c ='"+config.getMasterId()+"'");
		return sb.toString();
	}
	
	@Override
	public void preFormat(OpportunityDataC entity) {
		
		
	}

	@Override
	public void preFormat(Opportunity master, OpportunityDataC entity) {
		entity.setId(master.getName());
//		entity.setEPNNameC(master.getEPNNameC());
	}
	
	
}
