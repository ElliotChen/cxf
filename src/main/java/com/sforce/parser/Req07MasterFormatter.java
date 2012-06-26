package com.sforce.parser;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.column.Column;
import com.sforce.column.DoubleColumn;
import com.sforce.column.StringColumn;
import com.sforce.soap.enterprise.sobject.ProductOpportunityC;
import com.sforce.to.SfSqlConfig;
import com.sforce.util.DateUtils;
/**
 * id	Id
designInSiteIDC	Design_in_Site_ID__c
prospectGroupNoC	Prospect_Group_No__c
EPNC	EPN__c
applicationC	Application__c
SAMAvgAmountC	SAM_Avg_Amount__c Double
firstMPOrderNoC	First_MP_Order_No__c
 * @author elliot
 *
 */
public class Req07MasterFormatter extends BaseParser<ProductOpportunityC> {
	private static final Logger logger = LoggerFactory.getLogger(Req07MasterFormatter.class);
	
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
		columns.add(new StringColumn(i++, "id", "Id"));
		columns.add(new StringColumn(i++, "designInSiteIDC", "Design_in_Site_ID__c"));
		columns.add(new StringColumn(i++, "prospectGroupNoC", "Prospect_Group_No__c"));
		columns.add(new StringColumn(i++, "EPNC", "EPN__c"));
		columns.add(new StringColumn(i++, "applicationC", "Application__c"));
		columns.add(new DoubleColumn(i++, "SAMAvgAmountC", "SAM_Avg_Amount__c"));
		columns.add(new StringColumn(i++, "firstMPOrderNoC", "First_MP_Order_No__c"));
		
		this.tableName = "Product_Opportunity__c";
	}

	@Override
	public void buildSyncKey(ProductOpportunityC entity) {
	}

	protected String buildSfCondition(SfSqlConfig config) {
		StringBuilder sb = new StringBuilder();
		sb.append(" and First_MP_Order_No__c <> null and Check_Result__c = null ");
		if (null != config.getLasySyncDate()) {
			sb.append(" and LastModifiedDate > "+DateUtils.formatSfDateTime(config.getLasySyncDate()));
		}
		
		//TODO Design-in status = win?
		
		return sb.toString();
	}
	
}
