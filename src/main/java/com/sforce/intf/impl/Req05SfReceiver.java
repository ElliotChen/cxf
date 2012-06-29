package com.sforce.intf.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.domain.Job;
import com.sforce.parser.Parser;
import com.sforce.parser.Req05MasterFormatter;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.sobject.Opportunity;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.to.SfSqlConfig;

public class Req05SfReceiver extends SfReceiver {
	private static final Logger logger = LoggerFactory.getLogger(Req05SfReceiver.class);
	protected List<Parser<?>> parsers;
	
	private Req05MasterFormatter masterFormatter = new Req05MasterFormatter();

	public void doReceive(SfSqlConfig config, Job job) {
		File target = new File(job.getAbsolutePath());
		
		String queryString = masterFormatter.genSfSQL(config);
		logger.info(queryString);
		
		try {
			QueryResult query = this.soap.query(queryString, this.sh, null, null, null);
			String source = null;
			for (SObject so : query.getRecords()) {
				source = masterFormatter.format((Opportunity)so);
				//logger.info(source);
				FileUtils.write(target, source, true);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void postInit() {
		masterFormatter.init();
	}
}
