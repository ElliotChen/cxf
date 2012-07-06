package com.sforce.parser;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.column.Column;
import com.sforce.column.DateColumn;
import com.sforce.column.DoubleColumn;
import com.sforce.column.FakeColumn;
import com.sforce.column.MonthColumn;
import com.sforce.column.StringColumn;
import com.sforce.soap.enterprise.sobject.Opportunity;
import com.sforce.soap.enterprise.sobject.OpportunityHistory;
import com.sforce.to.SfSqlConfig;
/**
 * @author elliot
 *
 */
public class Req06I1AFormatter extends SubParser<OpportunityHistory, Opportunity> {
	private static final Logger logger = LoggerFactory.getLogger(Req06I1AFormatter.class);
	
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
		columns.add(new FakeColumn(i++, "I1A", ""));
		
		//43,3,2,5,5,36,10
				for (int index = 0; index < 43; index++) {
					columns.add(new FakeColumn(i++, "", ""));
				}
				
				
		columns.add(new StringColumn(i++, "id", ""));
		columns.add(new StringColumn(i++, "stageName", "StageName"));
		columns.add(new DateColumn(i++, "createdDate", "CreatedDate"));
		
		for (int index = 0; index < 2; index++) {
			columns.add(new FakeColumn(i++, "", ""));
		}
		for (int index = 0; index < 5; index++) {
			columns.add(new FakeColumn(i++, "", ""));
		}
		for (int index = 0; index < 5; index++) {
			columns.add(new FakeColumn(i++, "", ""));
		}
		for (int index = 0; index < 36; index++) {
			columns.add(new FakeColumn(i++, "", ""));
		}
		for (int index = 0; index < 10; index++) {
			columns.add(new FakeColumn(i++, "", ""));
		}
		this.tableName = "Opportunity.OpportunityHistories";
	}

	@Override
	public void postParse(OpportunityHistory entity) {
	}

	protected String buildSfCondition(SfSqlConfig config) {
		return "";
	}

	@Override
	public void preFormat(OpportunityHistory entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preFormat(Opportunity master, OpportunityHistory entity) {
		entity.setId(master.getName());
	}
	
	
}
