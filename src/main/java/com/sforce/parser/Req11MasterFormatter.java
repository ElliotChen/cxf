package com.sforce.parser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
 * 2012/08/01
 * 原查詢條件 Notes_Sync_ Date > Last_Sync_Date 改為 
 * Notes_Sync_ Date > Last_Sync_Date or Related_Person__r.Notes_Sync_Date__c > Last Sync Date
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
		columns.add(new StringColumn(i++, "accountTypeCodeC", "Account_Type_Code__c"));
		columns.add(new StringColumn(i++, "accountNumber", "AccountNumber"));
		columns.add(new StringColumn(i++, "documentStatusC", "Document_Status__c"));
		columns.add(new StringColumn(i++, "salesOrgC", "Sales_Org__c"));
		columns.add(new StringColumn(i++, "distChannelC", "Dist_Channel__c"));
		columns.add(new BooleanColumn(i++, "FBGCustomerC", "FBG_Customer__c", "1", ""));
		columns.add(new BooleanColumn(i++, "disabledDataC", "Disabled_Data__c", "1", ""));
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
		
//		columns.add(new StringColumn(i++, "otherRelatedPersonC", "Other_Related_Person__c"));
		columns.add(new StringColumn(i++, "relatedPersonC", "Related_Person__r.Related_Person__c"));//cheat01  relatedPersonC
		columns.add(new StringColumn(i++, "creatorUplinkC", "Creator_Uplink__c"));
		columns.add(new DateColumn(i++, "createdDateUplinkC", "CreatedDate_Uplink__c"));
		columns.add(new StringColumn(i++, "ownerUplinkC", "Owner_Uplink__c"));
		columns.add(new StringColumn(i++, "parentNameC", "Parent_Name__c"));
		
		this.tableName = "Account";
	}

	@Override
	public void postParse(Account entity) {
	}

	protected String buildSfCondition(SfSqlConfig config) {
		StringBuilder sb = new StringBuilder();
		Date lastDate = config.getLasySyncDate();
		if (null == lastDate) {
			Calendar cal = Calendar.getInstance(Locale.TAIWAN);
			cal.set(Calendar.MONTH, 0);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			lastDate = cal.getTime();
		}
		String lastSyncDate = DateUtils.formatSfDateTime(lastDate);
		sb.append(" and (Notes_Sync_Date__c > "+ lastSyncDate+" or Related_Person__r.Notes_Sync_Date__c > "+lastSyncDate+")");
		/*
		if (null != config.getLasySyncDate()) {
			sb.append(" and Notes_Sync_Date__c > "+DateUtils.formatSfDateTime(config.getLasySyncDate()));
		}
		*/		
		return sb.toString();
	}

	@Override
	public void preFormat(Account entity) {
		//cheat01  relatedPersonC
		if (null != entity.getRelatedPersonR()) {
			entity.setRelatedPersonC(entity.getRelatedPersonR().getRelatedPersonC());
		}
		/*
		if (null != entity.getCreatedBy()) {
			entity.setCreatedById(this.formateAsName(entity.getCreatedBy()));
		}
		
		if (null != entity.getOwner()) {
			entity.setOwnerId(this.formateAsName(entity.getOwner()));
		}
		*/
	}
	
}
