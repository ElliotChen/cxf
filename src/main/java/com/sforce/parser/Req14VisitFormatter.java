package com.sforce.parser;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
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
		return 12 == source.length && source[0].startsWith("V");
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
		columns.add(new StringColumn(i++, "createdById", "CreatedBy.Notes_Name__c"));//cheat
		columns.add(new DateColumn(i++, "visitDateC", "Visit_Date__c"));
		columns.add(new StringColumn(i++, "customerC", "Customer__r.Name"));//cheat
		columns.add(new StringColumn(i++, "productEPN2C", "Customer__r.AccountNumber")); //cheat...
		columns.add(new StringColumn(i++, "placeC", "Place__c"));
		columns.add(new StringColumn(i++, "categoryC", "Category__c")); //cheat04 recordTypeId
		
		//cheat
		columns.add(new StringColumn(i++, "productEPNName1C", "Product_EPN_Name1__c,Product_EPN_Name2__c,Product_EPN_Name3__c,Product_EPN_Name4__c,Product_EPN_Name5__c"));
		columns.add(new StringColumn(i++, "productBody1C", "Product_Body1__c,Product_Body2__c,Product_Body3__c,Product_Body4__c,Product_Body5__c"));
		columns.add(new DateColumn(i++, "customerIssueDateC", "Customer_Issue_Date__c"));
		
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
		if (null != entity.getCreatedBy()) {
			entity.setCreatedById(entity.getCreatedBy().getNotesNameC());
		}
		
		if (null != entity.getCustomerR()) {
			entity.setCustomerC(entity.getCustomerR().getName());
			entity.setProductEPN2C(entity.getCustomerR().getAccountNumber());
		}
		/*
		//cheat04 recordTypeId
		if (null != entity.getRecordType()) {
			entity.setRecordTypeId(entity.getRecordType().getName());
		}
		*/
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotEmpty(entity.getProductEPNName1C())) {
			sb.append(entity.getProductEPNName1C());
		}
		if (StringUtils.isNotEmpty(entity.getProductEPNName2C())) {
			sb.append(";"+entity.getProductEPNName2C());
		}
		if (StringUtils.isNotEmpty(entity.getProductEPNName3C())) {
			sb.append(";"+entity.getProductEPNName3C());
		}
		if (StringUtils.isNotEmpty(entity.getProductEPNName4C())) {
			sb.append(";"+entity.getProductEPNName4C());
		}
		if (StringUtils.isNotEmpty(entity.getProductEPNName5C())) {
			sb.append(";"+entity.getProductEPNName5C());
		}
		entity.setProductEPNName1C(sb.toString());
		
		sb = new StringBuilder();
		if (StringUtils.isNotEmpty(entity.getProductBody1C())) {
			sb.append(entity.getProductBody1C());
		}
		if (StringUtils.isNotEmpty(entity.getProductBody2C())) {
			sb.append(";"+entity.getProductBody2C());
		}
		if (StringUtils.isNotEmpty(entity.getProductBody3C())) {
			sb.append(";"+entity.getProductBody3C());
		}
		if (StringUtils.isNotEmpty(entity.getProductBody4C())) {
			sb.append(";"+entity.getProductBody4C());
		}
		if (StringUtils.isNotEmpty(entity.getProductBody5C())) {
			sb.append(";"+entity.getProductBody5C());
		}
		entity.setProductBody1C(sb.toString());
	}
	
}
