package com.sforce.parser;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.column.Column;
import com.sforce.column.DateColumn;
import com.sforce.column.FakeColumn;
import com.sforce.column.StringColumn;
import com.sforce.soap.enterprise.sobject.Opportunity;
import com.sforce.to.SfSqlConfig;
import com.sforce.util.DateUtils;
/**
 * 
 */
public class Req06MasterFormatter extends BaseParser<Opportunity> {
	private static final Logger logger = LoggerFactory.getLogger(Req06MasterFormatter.class);
	
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
		columns.add(new StringColumn(i++, "documentStatusC", "Document_Status__c"));
		columns.add(new StringColumn(i++, "stageName", "StageName"));
		columns.add(new StringColumn(i++, "stopTrackingTypeC", "Stop_Tracking_Type__c"));
		columns.add(new DateColumn(i++, "stopTrackingDateC", "Stop_Tracking_Date__c"));
		columns.add(new StringColumn(i++, "projectFailedReasonC", "Project_Failed_Reason__c"));
		columns.add(new StringColumn(i++, "projectFailedReasonRemarkC", "Project_Failed_Reason_Remark__c"));
		columns.add(new StringColumn(i++, "proposerCompanyNoC", "Proposer_Company_No__c"));
		columns.add(new FakeColumn(i++, "", ""));
		columns.add(new FakeColumn(i++, "", ""));
		
		columns.add(new StringColumn(i++, "majorApplicationC", "Major_Application__c"));
		columns.add(new StringColumn(i++, "realApplicationC", "Real_Application__c"));
		columns.add(new StringColumn(i++, "modelNameC", "Model_Name__c"));
		columns.add(new StringColumn(i++, "coreChipVendorC", "Core_chip_Vendor__c"));
		columns.add(new StringColumn(i++, "coreChipModelC", "Core_chip_Model__c"));
		columns.add(new StringColumn(i++, "brandCompanyC", "Brand_Company__c"));
		columns.add(new StringColumn(i++, "brandModelNameC", "Brand_Model_Name__c"));
		columns.add(new StringColumn(i++, "operatingSystemC", "Operating_System__c"));
		columns.add(new StringColumn(i++, "bootLoaderC", "Boot_Loader__c"));
		columns.add(new StringColumn(i++, "yearlyVolumeC", "Yearly_Volume__c"));
		
		columns.add(new StringColumn(i++, "accountId", "AccountId"));
		columns.add(new StringColumn(i++, "platformDescriptionC", "Platform_Description__c"));
		columns.add(new StringColumn(i++, "projectTypeC", "Project_Type__c"));
		columns.add(new StringColumn(i++, "repOrDistyC", "Rep_Or_Disty__c"));
		columns.add(new StringColumn(i++, "pvcSampleDateC", "PVC_Sample_Date__c"));
		columns.add(new DateColumn(i++, "closeDate", "CloseDate"));
		columns.add(new StringColumn(i++, "nextStep", "NextStep"));
		columns.add(new StringColumn(i++, "milestoneDateC", "Milestone_Date__c"));
		columns.add(new StringColumn(i++, "orderInFromC", "Order_in_from__c"));
		
		columns.add(new StringColumn(i++, "ownerId", "OwnerId"));
		columns.add(new FakeColumn(i++, "", ""));
		columns.add(new FakeColumn(i++, "", ""));
		columns.add(new FakeColumn(i++, "", ""));
		columns.add(new DateColumn(i++, "createdDate", "CreatedDate"));
		columns.add(new StringColumn(i++, "createdById", "CreatedById"));
		columns.add(new StringColumn(i++, "creatorDeptC", "Creator_Dept__c"));
		columns.add(new StringColumn(i++, "recordTypeC", "Record_Type__c"));
		columns.add(new StringColumn(i++, "DIURLC", "DI_URL__c"));
		columns.add(new DateColumn(i++, "bingoDateC", "Bingo_Date__c"));
		columns.add(new StringColumn(i++, "", ""));
		
		columns.add(new StringColumn(i++, "", ""));
		columns.add(new DateColumn(i++, "needBDIDateC", "Need_BDI_Date__c"));
		columns.add(new StringColumn(i++, "incentiveAppliedDateC", "Incentive_Applied_Date__c"));
		columns.add(new StringColumn(i++, "submitDateC", "Submit_Date__c"));
		columns.add(new StringColumn(i++, "lastModifiedById", "LastModifiedById"));
		columns.add(new StringColumn(i++, "lastModifiedDate", "LastModifiedDate"));
		
		this.tableName = "Opportunity";
	}

	@Override
	public void buildSyncKey(Opportunity entity) {
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
	public void preFormat(Opportunity entity) {
		// TODO Auto-generated method stub
		
	}
	
}
