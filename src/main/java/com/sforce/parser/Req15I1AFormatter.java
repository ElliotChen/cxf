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
		return 49 == source.length && "I1A".equals(source[0]);
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
		//41,3,2,2
		for (int fi = 0; fi < 41; fi++) {
			columns.add(new FakeColumn(i++, "", ""));
		}
		
		columns.add(new StringColumn(i++, "visitReportC", "")); //cheat master
		columns.add(new StringColumn(i++, "attendeeNameC", "Attendee_Name__r.FirstName,Attendee_Name__r.LastName"));//cheat
		columns.add(new StringColumn(i++, "departmentC", "Department__c"));
		
		
		for (int fi = 0; fi< 2; fi++) {
			columns.add(new FakeColumn(i++, "", ""));
		}
		
		for (int fi = 0; fi< 2; fi++) {
			columns.add(new FakeColumn(i++, "", ""));
		}
		this.tableName = "Visit_Report__c.Macronix_Site_Attendee__r";
	}

	@Override
	public void postParse(MacronixSiteAttendeeC entity) {
	}

	protected String buildSfCondition(SfSqlConfig config) {
		return "";
	}

	@Override
	public void preFormat(MacronixSiteAttendeeC entity) {
		if (null != entity.getAttendeeNameR()) {
			entity.setAttendeeNameC(this.formateAsName(entity.getAttendeeNameR()));
		}
	}

	@Override
	public void preFormat(VisitReportC master, MacronixSiteAttendeeC entity) {
		entity.setVisitReportC(master.getName());
	}
	
}
