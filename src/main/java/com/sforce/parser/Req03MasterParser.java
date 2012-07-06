package com.sforce.parser;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.column.Column;
import com.sforce.column.DateColumn;
import com.sforce.column.DoubleColumn;
import com.sforce.column.StringColumn;
import com.sforce.soap.enterprise.sobject.EPNProductBodyLinkC;
import com.sforce.soap.enterprise.sobject.ExchangeRateC;
/**
 * name	Name
productBodyC	Product_Body__c
maskOptC	Mask_Opt__c
beOptC	BE_Opt__c
releaseStatusC	Release_Status__c
fabc	FAB__c
markForDeleteC	Mark_for_Delete__c
 * @author elliot
 *
 */
public class Req03MasterParser extends BaseParser<EPNProductBodyLinkC> {
	private static final Logger logger = LoggerFactory.getLogger(Req03MasterParser.class);
	
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
		columns.add(new StringColumn(i++, "name", "Name"));
		columns.add(new StringColumn(i++, "productBodyC", "Product_Body__c"));
		columns.add(new StringColumn(i++, "maskOptC", "Mask_Opt__c"));
		columns.add(new StringColumn(i++, "BEOptC", "BE_Opt__c"));
		columns.add(new StringColumn(i++, "releaseStatusC", "Release_Status__c"));
		columns.add(new StringColumn(i++, "FABC", "FAB__c"));
		columns.add(new StringColumn(i++, "markForDeleteC", "Mark_for_Delete__c"));
	}

	@Override
	public void postParse(EPNProductBodyLinkC entity) {
		entity.setKeySyncC(entity.getName()+entity.getProductBodyC()+entity.getMaskOptC()+entity.getBEOptC());
	}

	@Override
	public void preFormat(EPNProductBodyLinkC entity) {
		// TODO Auto-generated method stub
		
	}

}
