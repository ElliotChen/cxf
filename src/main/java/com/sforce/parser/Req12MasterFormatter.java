package com.sforce.parser;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.column.Column;
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
		columns.add(new StringColumn(i++, "attributionC", "Attribution__c"));
		
		this.tableName = "Account";
	}

	@Override
	public void buildSyncKey(Account entity) {
	}

	protected String buildSfCondition(SfSqlConfig config) {
		StringBuilder sb = new StringBuilder();
		sb.append(" and Record_Type__c = 'SAP_Customer' ");
		if (null != config.getLasySyncDate()) {
			sb.append(" and SAP_Sync_Date__c > "+DateUtils.formatSfDateTime(config.getLasySyncDate()));
		}
		
		//TODO Design-in status = win?
		
		return sb.toString();
	}
	
}
