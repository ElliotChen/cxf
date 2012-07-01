package com.sforce.parser;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.column.Column;
import com.sforce.column.ComplexColumn;
import com.sforce.column.DateColumn;
import com.sforce.column.FakeColumn;
import com.sforce.column.StringColumn;
import com.sforce.column.TimeColumn;
import com.sforce.soap.enterprise.sobject.VisitReportC;
import com.sforce.to.SfSqlConfig;
/**
 *
 */
public class Req15MasterFormatter extends BaseParser<VisitReportC> {
	private static final Logger logger = LoggerFactory.getLogger(Req15MasterFormatter.class);
	
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
		columns.add(new FakeColumn(i++, "H", ""));
		
		columns.add(new StringColumn(i++, "recordTypeId", "RecordType.Name")); //cheating
		columns.add(new StringColumn(i++, "name", "Name"));
		columns.add(new StringColumn(i++, "statusC", "Status__c"));
		columns.add(new DateColumn(i++, "submitDateC", "Submit_Date__c"));
		columns.add(new DateColumn(i++, "visitDateC", "Visit_Date__c"));
		columns.add(new TimeColumn(i++, "startTimeC", "Start_Time__c"));
		columns.add(new TimeColumn(i++, "endTimeC", "End_Time__c"));
		columns.add(new StringColumn(i++, "topicPurposeC", "Topic_Purpose__c"));
		columns.add(new ComplexColumn(i++, "complaintsC", "Complaints__c"));
		columns.add(new StringColumn(i++, "highlightC", "Highlight__c"));
		
		columns.add(new StringColumn(i++, "issueTypeC", "Issue_Type__c"));
		columns.add(new StringColumn(i++, "initialSourceC", "Initial_Source__c"));
		columns.add(new StringColumn(i++, "initialSourceRemarkC", "Initial_Source_Remark__c"));
		columns.add(new StringColumn(i++, "initialSourceByC", "Initial_Source_by__c"));
		columns.add(new StringColumn(i++, "initialSourceByRemarkC", "Initial_Source_by_Remark__c"));
		columns.add(new StringColumn(i++, "customerIssueDateC", "Customer_Issue_Date__c"));
		columns.add(new StringColumn(i++, "customerIssueTimeC", "Customer_Issue_Time__c"));
		columns.add(new StringColumn(i++, "customerRequestVisitDateC", "Customer_Request_Visit_Date__c"));
		columns.add(new StringColumn(i++, "customerRequestVisitTimeC", "Customer_Request_Visit_Time__c"));
		columns.add(new StringColumn(i++, "customerC", "Customer__c"));
		
		columns.add(new StringColumn(i++, "productEPN1C", "Product_EPN1__c"));
		columns.add(new StringColumn(i++, "productEPN2C", "Product_EPN2__c"));
		columns.add(new StringColumn(i++, "productEPN3C", "Product_EPN3__c"));
		columns.add(new StringColumn(i++, "productEPN4C", "Product_EPN4__c"));
		columns.add(new StringColumn(i++, "productEPN5C", "Product_EPN5__c"));
		columns.add(new StringColumn(i++, "customerFailedModeC", "Customer_Failed_Mode__c"));
		columns.add(new StringColumn(i++, "customerFailedModeRemarkC", "Customer_Failed_Mode_Remark__c"));
		columns.add(new StringColumn(i++, "customerApplicationRemarkC", "Customer_Application_Remark__c"));
		columns.add(new StringColumn(i++, "titleOfSeminarShowC", "Title_of_Seminar_Show__c"));
		columns.add(new StringColumn(i++, "placeC", "Place__c"));
		
		columns.add(new StringColumn(i++, "createdById", "CreatedById"));
		columns.add(new StringColumn(i++, "createdDate", "CreatedDate"));
		columns.add(new StringColumn(i++, "ownerId", "OwnerId"));
		columns.add(new StringColumn(i++, "visitReportURLC", "Visit_Report_URL__c"));
		columns.add(new StringColumn(i++, "customerApplicationC", "Customer_Application__c"));
		columns.add(new StringColumn(i++, "purposeC", "Purpose__c"));
		
		this.tableName = "Visit_Report__c";
	}

	@Override
	public void buildSyncKey(VisitReportC entity) {
	}

	protected String buildSfCondition(SfSqlConfig config) {
		StringBuilder sb = new StringBuilder();
		/*
		sb.append(" and Status__c <> 'Draft' ");
		if (null != config.getLasySyncDate()) {
			sb.append(" and LastModifiedDate > "+DateUtils.formatSfDateTime(config.getLasySyncDate()));
		}
		*/
		
		return sb.toString();
	}

	@Override
	public void preFormat(VisitReportC entity) {
		// TODO Auto-generated method stub
		
	}
	
}
