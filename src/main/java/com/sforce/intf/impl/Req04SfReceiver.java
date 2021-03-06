package com.sforce.intf.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.domain.Job;
import com.sforce.parser.Parser;
import com.sforce.parser.Req04I1AFormatter;
import com.sforce.parser.Req04MasterFormatter;
import com.sforce.parser.SubParser;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.sobject.CompetitorPriceC;
import com.sforce.soap.enterprise.sobject.CompetitorPriceItemC;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.to.SfSqlConfig;

public class Req04SfReceiver extends SfReceiver {
	private static final Logger logger = LoggerFactory.getLogger(Req04SfReceiver.class);
	protected List<Parser<?>> parsers;
	
	private Req04MasterFormatter masterFormatter = new Req04MasterFormatter();
	private Req04I1AFormatter i1AFormatter = new Req04I1AFormatter();

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
			CompetitorPriceC cp = (CompetitorPriceC)so;
			source = masterFormatter.format(cp);
			this.write(target, source);
			
			QueryResult result = cp.getCompetitorPriceItemR();
			List<SObject> i1As = result.getRecords();
			if (null != i1As && !i1As.isEmpty()) {
				for (SObject i1A : i1As) {
					CompetitorPriceItemC cpi = (CompetitorPriceItemC)i1A;
					this.i1AFormatter.preFormat(cp, cpi);
					source = i1AFormatter.format(cpi);
					this.write(target, source);
				}
			}
			//logger.info(source);
			
		}
	}
	@Override
	public void postInit() {
		masterFormatter.init();
		i1AFormatter.init();
		List<SubParser<?,?>> subParsers = new ArrayList<SubParser<?,?>>();
		subParsers.add(i1AFormatter);
		masterFormatter.setSubParsers(subParsers);
	}
}
