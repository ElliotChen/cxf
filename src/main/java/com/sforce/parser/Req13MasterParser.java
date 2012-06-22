package com.sforce.parser;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.column.Column;
import com.sforce.column.DateColumn;
import com.sforce.column.DoubleColumn;
import com.sforce.column.StringColumn;
import com.sforce.soap.enterprise.sobject.CustomerCategoryC;

/**
 * 
 * @author elliot
 *
 */
public class Req13MasterParser extends BaseParser<CustomerCategoryC>{
	private static final Logger logger = LoggerFactory.getLogger(Req13MasterParser.class);
	@Override
	public boolean accept(String[] source) {
		return 6 == source.length;
	}

	@Override
	public Logger getLogger() {
		return logger;
	}

	@Override
	protected void initDefaultColumns() {
		this.columns = new ArrayList<Column>();
		int i = 0;
		columns.add(new StringColumn(i++, "accountNumberC", "Account_Number__c"));
		columns.add(new StringColumn(i++, "GLOBALACCTC", "GLOBAL_ACCT__c"));
		columns.add(new StringColumn(i++, "highdenacctc", "HIGH_DEN_ACCT__c"));
		columns.add(new StringColumn(i++, "baselineacctc", "BASELINE_ACCT__c"));
		columns.add(new StringColumn(i++, "aebp1ACCTC", "AEB_P1_ACCT__c"));
		columns.add(new StringColumn(i++, "aebp2ACCTC", "AEB_P2_ACCT__c"));
	}

	@Override
	public void buildSyncKey(CustomerCategoryC entity) {
		// TODO Auto-generated method stub
		
	}

}
