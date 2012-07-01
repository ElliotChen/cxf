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
import com.sforce.soap.enterprise.sobject.DIRelatedAccountC;
import com.sforce.soap.enterprise.sobject.Opportunity;
import com.sforce.soap.enterprise.sobject.OpportunityHistory;
import com.sforce.to.SfSqlConfig;
/**
 * 



 * @author elliot
 *
 */
public class Req06I3AFormatter extends SubParser<DIRelatedAccountC, Opportunity> {
	private static final Logger logger = LoggerFactory.getLogger(Req06I3AFormatter.class);
	
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
		columns.add(new FakeColumn(i++, "I3A", ""));
		
		columns.add(new StringColumn(i++, "id", ""));
		columns.add(new StringColumn(i++, "manufactureSiteC", "Manufacture_Site__c"));
		columns.add(new StringColumn(i++, "emsListC", "EMS_List__c"));
		columns.add(new StringColumn(i++, "manufactureSiteAMC", "Manufacture_Site_AM__c"));
		columns.add(new StringColumn(i++, "emsListAMC", "EMS_List_AM__c"));
		
		this.tableName = "Opportunity.DI_Related_Account__r";
	}

	@Override
	public void buildSyncKey(DIRelatedAccountC entity) {
	}

	protected String buildSfCondition(SfSqlConfig config) {
		return "";
	}

	@Override
	public void preFormat(DIRelatedAccountC entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preFormat(Opportunity master, DIRelatedAccountC entity) {
		entity.setId(master.getName());
	}
	
	
}
