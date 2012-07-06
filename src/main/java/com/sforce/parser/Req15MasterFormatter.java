package com.sforce.parser;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.column.Column;
import com.sforce.column.ComplexColumn;
import com.sforce.column.DateColumn;
import com.sforce.column.FakeColumn;
import com.sforce.column.StringColumn;
import com.sforce.soap.enterprise.sobject.VisitReportC;
import com.sforce.to.SfSqlConfig;
import com.sforce.util.DateUtils;
/**
 *
 */
public class Req15MasterFormatter extends BaseParser<VisitReportC> {
	private static final Logger logger = LoggerFactory.getLogger(Req15MasterFormatter.class);
	
	@Override
	public boolean accept(String[] source) {
		return 52 == source.length && "H".equals(source[0]);
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
		columns.add(new StringColumn(i++, "startTimeC", "Start_Time__c"));
		columns.add(new StringColumn(i++, "endTimeC", "End_Time__c"));
		columns.add(new StringColumn(i++, "topicPurposeC", "Topic_Purpose__c"));
		columns.add(new ComplexColumn(i++, "complaintsC", "Complaints__c"));
		columns.add(new StringColumn(i++, "highlightC", "Highlight__c"));
		
		columns.add(new StringColumn(i++, "issueTypeC", "Issue_Type__c"));
		columns.add(new StringColumn(i++, "initialSourceC", "Initial_Source__c"));
		columns.add(new StringColumn(i++, "initialSourceRemarkC", "Initial_Source_Remark__c"));
		columns.add(new StringColumn(i++, "initialSourceByC", "Initial_Source_by__c"));
		columns.add(new StringColumn(i++, "initialSourceByRemarkC", "Initial_Source_by_Remark__c"));
		columns.add(new DateColumn(i++, "customerIssueDateC", "Customer_Issue_Date__c"));
		columns.add(new StringColumn(i++, "customerIssueTimeC", "Customer_Issue_Time__c"));
		columns.add(new DateColumn(i++, "customerRequestVisitDateC", "Customer_Request_Visit_Date__c"));
		columns.add(new StringColumn(i++, "customerRequestVisitTimeC", "Customer_Request_Visit_Time__c"));
		columns.add(new StringColumn(i++, "customerC", "Customer__r.AccountNumber")); //cheat
		
		columns.add(new StringColumn(i++, "productEPNName1C", "Product_EPN_Name1__c"));
		columns.add(new StringColumn(i++, "productEPNName2C", "Product_EPN_Name2__c"));
		columns.add(new StringColumn(i++, "productEPNName3C", "Product_EPN_Name3__c"));
		columns.add(new StringColumn(i++, "productEPNName4C", "Product_EPN_Name4__c"));
		columns.add(new StringColumn(i++, "productEPNName5C", "Product_EPN_Name5__c"));
		columns.add(new StringColumn(i++, "productBody1C", "Product_Body1__c"));
		columns.add(new StringColumn(i++, "productBody2C", "Product_Body2__c"));
		columns.add(new StringColumn(i++, "productBody3C", "Product_Body3__c"));
		columns.add(new StringColumn(i++, "productBody4C", "Product_Body4__c"));
		columns.add(new StringColumn(i++, "productBody5C", "Product_Body5__c"));
		
		columns.add(new StringColumn(i++, "customerFailedModeC", "Customer_Failed_Mode__c"));
		columns.add(new StringColumn(i++, "customerFailedModeRemarkC", "Customer_Failed_Mode_Remark__c"));
		columns.add(new StringColumn(i++, "customerApplicationRemarkC", "Customer_Application_Remark__c"));
		columns.add(new StringColumn(i++, "titleOfSeminarShowC", "Title_of_Seminar_Show__c"));
		columns.add(new StringColumn(i++, "placeC", "Place__c"));
		
		columns.add(new StringColumn(i++, "createdById", "CreatedBy.FirstName,CreatedBy.LastName"));//cheat
		columns.add(new DateColumn(i++, "createdDate", "CreatedDate"));
		columns.add(new StringColumn(i++, "ownerId", "Owner.FirstName,Owner.LastName"));//cheat
		columns.add(new StringColumn(i++, "visitReportURLC", "Visit_Report_URL__c"));
		columns.add(new StringColumn(i++, "customerApplicationC", "Customer_Application__c"));
		columns.add(new StringColumn(i++, "purposeC", "Purpose__c"));
		//41,3,2,2
		for (int fi = 0; fi < 41; fi++) {
			columns.add(new FakeColumn(i++, "", ""));
		}
		
		for (int fi = 0; fi< 3; fi++) {
			columns.add(new FakeColumn(i++, "", ""));
		}
		
		for (int fi = 0; fi< 2; fi++) {
			columns.add(new FakeColumn(i++, "", ""));
		}
		
		for (int fi = 0; fi< 2; fi++) {
			columns.add(new FakeColumn(i++, "", ""));
		}
		this.tableName = "Visit_Report__c";
	}

	@Override
	public void postParse(VisitReportC entity) {
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
		if (null != entity.getCustomerR()) {
			entity.setCustomerC(entity.getCustomerR().getAccountNumber());
		}
		
		if (null != entity.getCreatedBy()) {
			entity.setCreatedById(this.formateAsName(entity.getCreatedBy()));
		}
		
		if (null != entity.getOwner()) {
			entity.setOwnerId(this.formateAsName(entity.getOwner()));
		}
	}
	
}
