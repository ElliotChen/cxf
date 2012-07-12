package com.sforce.parser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.column.Column;
import com.sforce.column.ComplexColumn;
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
public class Req12MasterFormatter extends BaseParser<Account> {
	private static final Logger logger = LoggerFactory.getLogger(Req12MasterFormatter.class);
	
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
		columns.add(new StringColumn(i++, "accountNumber", "AccountNumber"));
		columns.add(new StringColumn(i++, "parentIDC", "Parent_ID__c"));
		columns.add(new ComplexColumn(i++, "attributionC", "Attribution__c"));
		
		this.tableName = "Account";
	}

	@Override
	public void postParse(Account entity) {
	}

	protected String buildSfCondition(SfSqlConfig config) {
		StringBuilder sb = new StringBuilder();
		sb.append(" and Account_Type_Code__c = 'S' ");
		
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
		
		sb.append(" and Notes_Sync_Date__c > "+DateUtils.formatSfDateTime(lastDate));
		/*
		if (null != config.getLasySyncDate()) {
			sb.append(" and SAP_Sync_Date__c > "+DateUtils.formatSfDateTime(config.getLasySyncDate()));
		}
		*/
		return sb.toString();
	}

	@Override
	public void preFormat(Account entity) {
		// TODO Auto-generated method stub
		
	}
	
}
