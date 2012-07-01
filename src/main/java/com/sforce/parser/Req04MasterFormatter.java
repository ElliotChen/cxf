package com.sforce.parser;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.column.Column;
import com.sforce.column.DateColumn;
import com.sforce.column.FakeColumn;
import com.sforce.column.StringColumn;
import com.sforce.column.TimeColumn;
import com.sforce.soap.enterprise.sobject.CompetitorPriceC;
import com.sforce.to.SfSqlConfig;
import com.sforce.util.DateUtils;
/**
 *
 */
public class Req04MasterFormatter extends BaseParser<CompetitorPriceC> {
	private static final Logger logger = LoggerFactory.getLogger(Req04MasterFormatter.class);
	
	@Override
	public boolean accept(String[] source) {
		return 2 == source.length;
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
		
		columns.add(new StringColumn(i++, "name", "Name"));
		columns.add(new StringColumn(i++, "statusC", "Status__c"));
		columns.add(new DateColumn(i++, "submitDateC", "Submit_Date__c"));
		columns.add(new TimeColumn(i++, "submitDateC", ""));
		columns.add(new StringColumn(i++, "productTypeC", "Product_Type__c"));
		columns.add(new StringColumn(i++, "applicationC", "Application__c"));
		columns.add(new StringColumn(i++, "densityC", "Density__c"));
		columns.add(new StringColumn(i++, "compatibleMXICEPNC", "Compatible_MXIC_EPN__c"));
		columns.add(new StringColumn(i++, "gradeC", "Grade__c"));
		columns.add(new StringColumn(i++, "packageNameC", "Package_Name__c"));
		
		columns.add(new StringColumn(i++, "pinCountC", "Pin_Count__c"));
		columns.add(new StringColumn(i++, "remarkC", "Remark__c"));
		columns.add(new StringColumn(i++, "customerC", "Customer__c"));
		columns.add(new StringColumn(i++, "customerEnglishShortNameC", "Customer_English_Short_Name__c"));
		columns.add(new StringColumn(i++, "customerRegionC", "Customer_Region__c"));
		columns.add(new StringColumn(i++, "groupIDC", "Group_ID__c"));
		columns.add(new StringColumn(i++, "groupEnglishShortNameC", "Group_English_Short_Name__c"));
		columns.add(new StringColumn(i++, "createdById", "CreatedById"));
		columns.add(new DateColumn(i++, "createdDate", "CreatedDate"));
		columns.add(new StringColumn(i++, "ownerId", "OwnerId"));
		
		columns.add(new StringColumn(i++, "visitReportDocNoC", "Visit_Report_Doc_No__c"));
		columns.add(new StringColumn(i++, "visitReportURLC", "Visit_Report_URL__c"));
		columns.add(new StringColumn(i++, "relatedPersonC", "Related_Person__c"));
		
		
		this.tableName = "Competitor_price__c";
	}

	@Override
	public void buildSyncKey(CompetitorPriceC entity) {
	}

	protected String buildSfCondition(SfSqlConfig config) {
		StringBuilder sb = new StringBuilder();
		sb.append(" and Status__c = 'Submit' ");
		if (null != config.getLasySyncDate()) {
			sb.append(" and LastModifiedDate > "+DateUtils.formatSfDateTime(config.getLasySyncDate()));
		}
		
		//TODO Design-in status = win?
		
		return sb.toString();
	}

	@Override
	public void preFormat(CompetitorPriceC entity) {
		// TODO Auto-generated method stub
		
	}
	
}
