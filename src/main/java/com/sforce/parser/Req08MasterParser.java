package com.sforce.parser;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.column.Column;
import com.sforce.column.StringColumn;
import com.sforce.soap.enterprise.sobject.ApplicationC;
import com.sforce.soap.enterprise.sobject.ProductOpportunityC;

/**
 * Id	Id
checkResultC	Check_Result__c
designInSiteIDC	Design_in_Site_ID__c
 * @author elliot
 *
 */
public class Req08MasterParser extends BaseParser<ProductOpportunityC>{
	private static final Logger logger = LoggerFactory.getLogger(Req08MasterParser.class);

	protected void initDefaultColumns() {
		this.columns = new ArrayList<Column<?>>();
		int i = 0;
		columns.add(new StringColumn(i++, "id", "Id"));
		columns.add(new StringColumn(i++, "checkResultC", "Check_Result__c"));
//		columns.add(new StringColumn(i++, "designInSiteIDC", "Design_in_Site_ID__c"));
		columns.add(new StringColumn(i++, "actualOrderInPartyC", "Actual_Order_in_Party__c"));
	}
	/*
	public ApplicationC parse(String[] source) {
		ApplicationC target = new ApplicationC();
		for (Column col : columns) {
			String s = source[col.getIndex()];
			try {
				if (null != col.getWriteMethod()) {
					col.getWriteMethod().invoke(target, col.parse(s));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return target;
	}
	*/
	public boolean accept(String[] source) {
		return 3 == source.length;
	}
	
	
	@Override
	public Logger getLogger() {
		return logger;
	}

	@Override
	public void postParse(ProductOpportunityC entity) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void preFormat(ProductOpportunityC entity) {
		// TODO Auto-generated method stub
		
	}
}
