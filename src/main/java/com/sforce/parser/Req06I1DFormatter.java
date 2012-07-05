package com.sforce.parser;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.column.BooleanColumn;
import com.sforce.column.Column;
import com.sforce.column.DateColumn;
import com.sforce.column.FakeColumn;
import com.sforce.column.StringColumn;
import com.sforce.soap.enterprise.sobject.KeyMilestoneC;
import com.sforce.soap.enterprise.sobject.Opportunity;
import com.sforce.to.SfSqlConfig;
/**
 * 






 * @author elliot
 *
 */
public class Req06I1DFormatter extends SubParser<KeyMilestoneC, Opportunity> {
	private static final Logger logger = LoggerFactory.getLogger(Req06I1DFormatter.class);
	
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
		columns.add(new FakeColumn(i++, "I1D", ""));
		
		columns.add(new StringColumn(i++, "id", ""));
		columns.add(new StringColumn(i++, "name", "Name"));
		columns.add(new DateColumn(i++, "keyMilestoneDateC", "Key_Milestone_Date__c"));
		columns.add(new StringColumn(i++, "bingoDocNoNameC", "Bingo_Doc_No_Name__c"));
		columns.add(new StringColumn(i++, "isCurrentMilestoneC", "Is_Current_Milestone__c"));
		
		this.tableName = "Opportunity.Key_Milestones__r";
	}

	@Override
	public void buildSyncKey(KeyMilestoneC entity) {
	}

	protected String buildSfCondition(SfSqlConfig config) {
		return "";
	}

	@Override
	public void preFormat(KeyMilestoneC entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preFormat(Opportunity master, KeyMilestoneC entity) {
		entity.setId(master.getName());
	}
	
	
}
