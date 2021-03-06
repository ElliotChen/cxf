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
public class Req06I1CFormatter extends SubParser<DIRelatedAccountC, Opportunity> {
	private static final Logger logger = LoggerFactory.getLogger(Req06I1CFormatter.class);
	
	@Override
	public boolean accept(String[] source) {
		return 105 == source.length && "I1C".equals(source[0]);
	}

	@Override
	public Logger getLogger() {
		return logger;
	}

	@Override
	protected void initDefaultColumns() {
		this.columns = new ArrayList<Column<?>>();
		int i = 0;
		columns.add(new FakeColumn(i++, "I1C", ""));
		//43,3,2,5,36,10
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
				
				
		columns.add(new StringColumn(i++, "id", ""));
		columns.add(new StringColumn(i++, "manufactureSiteIDC", "Manufacture_Site_ID__c, Target_Customer_ID__c"));
		columns.add(new StringColumn(i++, "EMSListIDC", "EMS_List_ID__c"));
		columns.add(new StringColumn(i++, "manufactureSiteAMC", "Manufacture_Site_AM__c"));
		columns.add(new StringColumn(i++, "EMSListAMC", "EMS_List_AM__c"));
		for (int index = 0; index < 5; index++) {
			columns.add(new FakeColumn(i++, "", ""));
		}
		for (int index = 0; index < 36; index++) {
			columns.add(new FakeColumn(i++, "", ""));
		}
		for (int index = 0; index < 10; index++) {
			columns.add(new FakeColumn(i++, "", ""));
		}
		this.tableName = "Opportunity.DI_Related_Account__r";
		
		//this.wcondition = "Target_Customer_ID__c <> null";
	}

	@Override
	public void postParse(DIRelatedAccountC entity) {
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
