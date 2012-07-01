package com.sforce.parser;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.column.Column;
import com.sforce.column.FakeColumn;
import com.sforce.column.StringColumn;
import com.sforce.soap.enterprise.sobject.MacronixSiteAttendeeC;
import com.sforce.soap.enterprise.sobject.VisitReportC;
import com.sforce.to.SfSqlConfig;
/**
 *
 */
public class Req15I1AFormatter extends SubParser<MacronixSiteAttendeeC, VisitReportC> {
	private static final Logger logger = LoggerFactory.getLogger(Req15I1AFormatter.class);
	
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
		columns.add(new FakeColumn(i++, "I1A", ""));
		
		columns.add(new StringColumn(i++, "visitReportC", "Visit_Report__c"));
		columns.add(new StringColumn(i++, "attendeeNameC", "Attendee_Name__c"));
		columns.add(new StringColumn(i++, "departmentC", "Department__c"));
		
		this.tableName = "Visit_Report__c.Macronix_Site_Attendee__r";
	}

	@Override
	public void buildSyncKey(MacronixSiteAttendeeC entity) {
	}

	protected String buildSfCondition(SfSqlConfig config) {
		return "";
	}

	@Override
	public void preFormat(MacronixSiteAttendeeC entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preFormat(VisitReportC master, MacronixSiteAttendeeC entity) {
		entity.setVisitReportC(master.getName());
	}
	
}