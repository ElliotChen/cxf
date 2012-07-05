package com.sforce.parser;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.column.Column;
import com.sforce.column.StringColumn;
import com.sforce.soap.enterprise.sobject.EPNProductBodyLinkC;
import com.sforce.soap.enterprise.sobject.Opportunity;
import com.sforce.to.SfSqlConfig;
import com.sforce.util.DateUtils;
/**
 * @author elliot
 *
 */
public class Req05MasterFormatter extends BaseParser<Opportunity> {
	private static final Logger logger = LoggerFactory.getLogger(Req05MasterFormatter.class);
	
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
		columns.add(new StringColumn(i++, "name", "Name"));
		columns.add(new StringColumn(i++, "DIURLC", "DI_URL__c"));
		
		this.tableName = "Opportunity";
	}

	@Override
	public void buildSyncKey(Opportunity entity) {
	}

	protected String buildSfCondition(SfSqlConfig config) {
		StringBuilder sb = new StringBuilder();
		sb.append(" and (Record_Type__c = 'BDI' or Record_Type__c = 'CDI') ");
		sb.append(" and StageName = 'Design-win' ");
		if (null != config.getLasySyncDate()) {
			sb.append(" and CreatedDate > "+DateUtils.formatSfDateTime(config.getLasySyncDate()));
		}
		return sb.toString();
	}

	@Override
	public void preFormat(Opportunity entity) {
		// TODO Auto-generated method stub
		
	}
	
}
