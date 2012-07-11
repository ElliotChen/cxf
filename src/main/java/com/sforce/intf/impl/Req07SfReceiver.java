package com.sforce.intf.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.domain.Job;
import com.sforce.parser.Parser;
import com.sforce.parser.Req07MasterFormatter;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.sobject.Opportunity;
import com.sforce.soap.enterprise.sobject.ProductOpportunityC;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.to.SfSqlConfig;

public class Req07SfReceiver extends SfReceiver {
	private static final Logger logger = LoggerFactory.getLogger(Req07SfReceiver.class);
	protected List<Parser<?>> parsers;
	
	private Req07MasterFormatter masterFormatter = new Req07MasterFormatter();

	public void doReceive(SfSqlConfig config, Job job) {
		File target = new File(job.getAbsolutePath());
		
		String queryString = masterFormatter.genSfSQL(config);
		logger.info(queryString);
		
		try {
			QueryResult query = this.soap.query(queryString, this.sh, null, null, null);
			this.handleQuery(query, target);
			
			while (!query.getDone()) {
				query = this.soap.queryMore(query.getQueryLocator(), this.sh, null);
				this.handleQuery(query, target);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void handleQuery(QueryResult query, File target) {
		String source = null;
		for (SObject so : query.getRecords()) {
			source = masterFormatter.format((ProductOpportunityC)so);
			this.write(target, source);
		}
	}
	
	@Override
	public void postInit() {
		masterFormatter.init();
	}
}
