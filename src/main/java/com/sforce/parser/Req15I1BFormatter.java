package com.sforce.parser;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.column.Column;
import com.sforce.column.FakeColumn;
import com.sforce.column.StringColumn;
import com.sforce.soap.enterprise.sobject.OtherRelatedGroupC;
import com.sforce.soap.enterprise.sobject.VisitReportC;
import com.sforce.to.SfSqlConfig;
/**
 *
 */
public class Req15I1BFormatter extends SubParser<OtherRelatedGroupC, VisitReportC> {
	private static final Logger logger = LoggerFactory.getLogger(Req15I1BFormatter.class);
	
	@Override
	public boolean accept(String[] source) {
		return 49 == source.length && "I1B".equals(source[0]);
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
		//41,3,2,2
		for (int fi = 0; fi < 41; fi++) {
			columns.add(new FakeColumn(i++, "", ""));
		}
				
		for (int fi = 0; fi< 3; fi++) {
			columns.add(new FakeColumn(i++, "", ""));
		}
		
		columns.add(new StringColumn(i++, "visitReportC", "")); //cheat for master
		columns.add(new StringColumn(i++, "groupNameFomulaC", "Group_Name_Fomula__c"));
		
		
		for (int fi = 0; fi< 2; fi++) {
			columns.add(new FakeColumn(i++, "", ""));
		}
		this.tableName = "Visit_Report__c.Other_Related_Group__r";
	}

	@Override
	public void postParse(OtherRelatedGroupC entity) {
	}

	protected String buildSfCondition(SfSqlConfig config) {
		return "";
	}

	@Override
	public void preFormat(OtherRelatedGroupC entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preFormat(VisitReportC master, OtherRelatedGroupC entity) {
		entity.setVisitReportC(master.getName());
	}
	
}
