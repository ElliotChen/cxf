package com.sforce.parser;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.column.Column;
import com.sforce.column.FakeColumn;
import com.sforce.column.StringColumn;
import com.sforce.soap.enterprise.sobject.RelatedApplicationC;
import com.sforce.soap.enterprise.sobject.VisitReportC;
import com.sforce.to.SfSqlConfig;
/**
 *
 */
public class Req15I1CFormatter extends SubParser<RelatedApplicationC, VisitReportC> {
	private static final Logger logger = LoggerFactory.getLogger(Req15I1CFormatter.class);
	
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
		columns.add(new FakeColumn(i++, "I1C", ""));
		
		columns.add(new StringColumn(i++, "visitReportC", "Visit_Report__c")); //cheat
		columns.add(new StringColumn(i++, "applicationC", "Application__c"));
		
		this.tableName = "Visit_Report__c.Related_Applications__r";
	}

	@Override
	public void buildSyncKey(RelatedApplicationC entity) {
	}

	protected String buildSfCondition(SfSqlConfig config) {
		return "";
	}

	@Override
	public void preFormat(RelatedApplicationC entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preFormat(VisitReportC master, RelatedApplicationC entity) {
		entity.setVisitReportC(master.getName());
		
	}
	
}
