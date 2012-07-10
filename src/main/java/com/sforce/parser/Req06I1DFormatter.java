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
		return 105 == source.length && "I1D".equals(source[0]);
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
				
		columns.add(new StringColumn(i++, "id", ""));
		columns.add(new StringColumn(i++, "name", "Name"));
		columns.add(new DateColumn(i++, "keyMilestoneDateC", "Key_Milestone_Date__c"));
		columns.add(new StringColumn(i++, "bingoDocNoNameC", "Bingo_Doc_No_Name__c"));
		columns.add(new StringColumn(i++, "isCurrentMilestoneC", "Is_Current_Milestone__c"));
		for (int index = 0; index < 36; index++) {
			columns.add(new FakeColumn(i++, "", ""));
		}
		for (int index = 0; index < 10; index++) {
			columns.add(new FakeColumn(i++, "", ""));
		}
		this.tableName = "Opportunity.Key_Milestones__r";
	}

	@Override
	public void postParse(KeyMilestoneC entity) {
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
