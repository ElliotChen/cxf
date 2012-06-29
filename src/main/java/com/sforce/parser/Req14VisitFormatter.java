package com.sforce.parser;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.column.Column;
import com.sforce.column.DateColumn;
import com.sforce.column.FakeColumn;
import com.sforce.column.StringColumn;
import com.sforce.soap.enterprise.sobject.TripReportC;
import com.sforce.soap.enterprise.sobject.VisitReportC;
import com.sforce.to.SfSqlConfig;
import com.sforce.util.DateUtils;
/**
Name












 * @author elliot
 *
 */
public class Req14VisitFormatter extends BaseParser<VisitReportC> {
	private static final Logger logger = LoggerFactory.getLogger(Req14VisitFormatter.class);
	
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
		columns.add(new StringColumn(i++, "visitReportURLC", "Visit_Report_URL__c"));
		columns.add(new DateColumn(i++, "submitDateC", "Submit_Date__c"));
		columns.add(new StringColumn(i++, "createdById", "CreatedById"));
		columns.add(new DateColumn(i++, "visitDateC", "Visit_Date__c"));
		columns.add(new StringColumn(i++, "customerC", "Customer__c"));
		columns.add(new StringColumn(i++, "productEPN2C", "Customer__r.Id")); //cheat...
		columns.add(new StringColumn(i++, "placeC", "Place__c"));
		columns.add(new StringColumn(i++, "recordTypeId", "RecordType.Name"));
		
		columns.add(new StringColumn(i++, "productEPN1C", "Product_EPN1__c"));
		columns.add(new FakeColumn(i++, "", "Product_EPN2__c"));
		columns.add(new FakeColumn(i++, "", "Product_EPN3__c"));
		columns.add(new FakeColumn(i++, "", "Product_EPN4__c"));
		columns.add(new FakeColumn(i++, "", "Product_EPN5__c"));
		//@TODO Product Body?
		
		columns.add(new DateColumn(i++, "customerIssueDateC", "Customer_Issue_Date__c"));
		
		this.tableName = "Visit_Report__c";
	}

	@Override
	public void buildSyncKey(VisitReportC entity) {
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
	public void preFormat(VisitReportC entity) {
		// TODO Auto-generated method stub
		
	}
	
}
