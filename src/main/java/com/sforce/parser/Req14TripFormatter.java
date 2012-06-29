package com.sforce.parser;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.column.Column;
import com.sforce.column.DateColumn;
import com.sforce.column.FakeColumn;
import com.sforce.column.StringColumn;
import com.sforce.soap.enterprise.sobject.TripReportC;
import com.sforce.to.SfSqlConfig;
import com.sforce.util.DateUtils;
/**





 * @author elliot
 *
 */
public class Req14TripFormatter extends BaseParser<TripReportC> {
	private static final Logger logger = LoggerFactory.getLogger(Req14TripFormatter.class);
	
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
		columns.add(new StringColumn(i++, "name", "Name"));
		columns.add(new StringColumn(i++, "tripReportURLC", "Trip_Report_URL__c"));
		columns.add(new DateColumn(i++, "submitDateC", "Submit_Date__c"));
		columns.add(new StringColumn(i++, "createdById", "CreatedById"));
		columns.add(new DateColumn(i++, "tripFromDateC", "Trip_From_Date__c"));
		columns.add(new FakeColumn(i++, "", ""));
		columns.add(new FakeColumn(i++, "", ""));
		columns.add(new FakeColumn(i++, "", ""));
		columns.add(new FakeColumn(i++, "", ""));
		columns.add(new FakeColumn(i++, "", ""));
		columns.add(new FakeColumn(i++, "", ""));
		columns.add(new FakeColumn(i++, "", ""));
		
		this.tableName = "Trip_Report__c";
	}

	@Override
	public void buildSyncKey(TripReportC entity) {
	}

	protected String buildSfCondition(SfSqlConfig config) {
		StringBuilder sb = new StringBuilder();
		sb.append(" and Status__c <> 'Draft' ");
		if (null != config.getLasySyncDate()) {
			sb.append(" and LastModifiedDate > "+DateUtils.formatSfDateTime(config.getLasySyncDate()));
		}
		
		return sb.toString();
	}

	@Override
	public void preFormat(TripReportC entity) {
		// TODO Auto-generated method stub
		
	}
	
}
