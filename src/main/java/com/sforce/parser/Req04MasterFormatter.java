package com.sforce.parser;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.column.Column;
import com.sforce.column.FakeColumn;
import com.sforce.column.StringColumn;
import com.sforce.soap.enterprise.sobject.CompetitorPriceC;
import com.sforce.soap.enterprise.sobject.Opportunity;
import com.sforce.to.SfSqlConfig;
import com.sforce.util.DateUtils;
/**
 * @author elliot
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
		columns.add(new StringColumn(i++, "DIURLC", "DI_URL__c"));
		
		this.tableName = "Opportunity";
	}

	@Override
	public void buildSyncKey(CompetitorPriceC entity) {
	}

	protected String buildSfCondition(SfSqlConfig config) {
		StringBuilder sb = new StringBuilder();
		sb.append(" and (Record_Type__c = 'BDI' or Record_Type__c = 'CDI') ");
		if (null != config.getLasySyncDate()) {
			sb.append(" and CreatedDate > "+DateUtils.formatSfDateTime(config.getLasySyncDate()));
		}
		
		//TODO Design-in status = win?
		
		return sb.toString();
	}
	
}
