package com.sforce.parser;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.column.BooleanColumn;
import com.sforce.column.Column;
import com.sforce.column.DateColumn;
import com.sforce.column.StringColumn;
import com.sforce.soap.enterprise.sobject.Account;
import com.sforce.to.SfSqlConfig;
import com.sforce.util.DateUtils;
/**
 * accountNumber	AccountNumber
 * parentIDC	Parent_ID__c
 * attributionC	Attribution__c
 * @author elliot
 *
 */
public class Req11MasterFormatter extends BaseParser<Account> {
	private static final Logger logger = LoggerFactory.getLogger(Req11MasterFormatter.class);
	
	@Override
	public boolean accept(String[] source) {
		return 35 == source.length;
	}

	@Override
	public Logger getLogger() {
		return logger;
	}

	@Override
	protected void initDefaultColumns() {
		this.columns = new ArrayList<Column<?>>();
		int i = 0;
		columns.add(new StringColumn(i++, "recordTypeC", "Record_Type__c"));
		columns.add(new StringColumn(i++, "accountNumber", "AccountNumber"));
		columns.add(new StringColumn(i++, "documentStatusC", "Document_Status__c"));
		columns.add(new StringColumn(i++, "salesOrgC", "Sales_Org__c"));
		columns.add(new StringColumn(i++, "distChannelC", "Dist_Channel__c"));
		columns.add(new BooleanColumn(i++, "FBGCustomerC", "FBG_Customer__c"));
		columns.add(new BooleanColumn(i++, "disabledDataC", "Disabled_Data__c"));
		columns.add(new DateColumn(i++, "submitDateC", "Submit_Date__c"));
		columns.add(new StringColumn(i++, "name", "Name"));
		columns.add(new StringColumn(i++, "localLanguageShortNameC", "Local_Language_Short_Name__c"));
		
		columns.add(new StringColumn(i++, "englishFullNameC", "English_Full_Name__c"));
		columns.add(new StringColumn(i++, "localLanguageFullNameC", "Local_Language_Full_Name__c"));
		columns.add(new StringColumn(i++, "streetHouseNumberC", "Street_House_Number__c"));
		columns.add(new StringColumn(i++, "postalCodeC", "Postal_Code__c"));
		columns.add(new StringColumn(i++, "cityC", "City__c"));
		columns.add(new StringColumn(i++, "stateProvinceIDC", "State_Province_ID__c"));
		columns.add(new StringColumn(i++, "stateProvinceNameC", "State_Province_Name__c"));
		columns.add(new StringColumn(i++, "countryIDC", "Country_ID__c"));
		columns.add(new StringColumn(i++, "countryNameC", "Country_Name__c"));
		columns.add(new StringColumn(i++, "website", "Website"));
		
		columns.add(new StringColumn(i++, "phone", "Phone"));
		columns.add(new StringColumn(i++, "fax", "Fax"));
		columns.add(new StringColumn(i++, "regionC", "Region__c"));
		columns.add(new StringColumn(i++, "applicationIDC", "Application_ID__c"));
		columns.add(new StringColumn(i++, "applicationNameC", "Application_Name__c"));
		columns.add(new StringColumn(i++, "attributionC", "Attribution__c"));
		columns.add(new StringColumn(i++, "parentIDC", "Parent_ID__c"));
		columns.add(new StringColumn(i++, "SAPCustomerNoC", "SAP_Customer_No__c"));
		columns.add(new DateColumn(i++, "expiredDateC", "Expired_Date__c"));
		columns.add(new StringColumn(i++, "expiredActivateReasonC", "Expired_Activate_Reason__c"));
		
		columns.add(new StringColumn(i++, "otherRelatedPersonC", "Other_Related_Person__c"));
		columns.add(new StringColumn(i++, "createdById", "CreatedById"));
		columns.add(new DateColumn(i++, "createdDate", "CreatedDate"));
		columns.add(new StringColumn(i++, "ownerId", "OwnerId"));
		columns.add(new StringColumn(i++, "parentId", "ParentId"));
		
		this.tableName = "Account";
	}

	@Override
	public void buildSyncKey(Account entity) {
	}

	protected String buildSfCondition(SfSqlConfig config) {
		StringBuilder sb = new StringBuilder();
		if (null != config.getLasySyncDate()) {
			sb.append(" and Notes_Sync_Date__c > "+DateUtils.formatSfDateTime(config.getLasySyncDate()));
		}
		
		//TODO Design-in status = win?
		
		return sb.toString();
	}
	
}
