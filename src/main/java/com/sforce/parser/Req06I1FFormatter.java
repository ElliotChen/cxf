package com.sforce.parser;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.column.Column;
import com.sforce.column.DateColumn;
import com.sforce.column.DoubleColumn;
import com.sforce.column.FakeColumn;
import com.sforce.column.MonthColumn;
import com.sforce.column.StringColumn;
import com.sforce.soap.enterprise.sobject.DIMilestoneHistoryC;
import com.sforce.soap.enterprise.sobject.DIRelatedAccountC;
import com.sforce.soap.enterprise.sobject.Opportunity;
import com.sforce.soap.enterprise.sobject.OpportunityDataC;
import com.sforce.soap.enterprise.sobject.OpportunityHistory;
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
		
		columns.add(new StringColumn(i++, "id", ""));
		columns.add(new StringColumn(i++, "EPNC", "EPN__c"));
		columns.add(new DateColumn(i++, "startDateC", "Start_Date__c"));
		columns.add(new StringColumn(i++, "periodTypeC", "Period_Type__c"));
		columns.add(new DoubleColumn(i++, "monthQtyC", "Month_Qty__c"));
		columns.add(new StringColumn(i++, "currencyC", "Currency__c"));
		columns.add(new DoubleColumn(i++, "quotePriceC", "Quote_Price__c"));
		columns.add(new FakeColumn(i++, "", ""));
		columns.add(new DoubleColumn(i++, "SAMQtyKeaC", "SAM_Qty_Kea__c"));
		columns.add(new DoubleColumn(i++, "SOMQtyKeaC", "SOM_Qty_Kea__c"));
		
		this.tableName = "Opportunity.Opportunity_Data__r";
	}

	@Override
	public void buildSyncKey(OpportunityDataC entity) {
	}

	protected String buildSfCondition(SfSqlConfig config) {
		return "";
	}

	@Override
	public void preFormat(OpportunityDataC entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preFormat(Opportunity master, OpportunityDataC entity) {
		entity.setId(master.getName());
	}
	
	
}
