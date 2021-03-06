package com.sforce.intf.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.domain.Job;
import com.sforce.parser.Parser;
import com.sforce.parser.Req06I1AFormatter;
import com.sforce.parser.Req06I1BFormatter;
import com.sforce.parser.Req06I1CFormatter;
import com.sforce.parser.Req06I1DFormatter;
import com.sforce.parser.Req06I1EFormatter;
import com.sforce.parser.Req06I1FFormatter;
import com.sforce.parser.Req06MasterFormatter;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.sobject.DICaseStatusC;
import com.sforce.soap.enterprise.sobject.DIRelatedAccountC;
import com.sforce.soap.enterprise.sobject.KeyMilestoneC;
import com.sforce.soap.enterprise.sobject.Opportunity;
import com.sforce.soap.enterprise.sobject.OpportunityDataC;
import com.sforce.soap.enterprise.sobject.ProductOpportunityC;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.to.SfSqlConfig;

public class Req06SfReceiver extends SfReceiver {
	private static final Logger logger = LoggerFactory.getLogger(Req06SfReceiver.class);
	protected List<Parser<?>> parsers;
	
	private Req06MasterFormatter masterFormatter = new Req06MasterFormatter();
	private Req06I1AFormatter i1aFormatter = new Req06I1AFormatter();
	private Req06I1BFormatter i1bFormatter = new Req06I1BFormatter();
	private Req06I1CFormatter i1cFormatter = new Req06I1CFormatter();
	private Req06I1DFormatter i1dFormatter = new Req06I1DFormatter();
	private Req06I1EFormatter i1eFormatter = new Req06I1EFormatter();
	private Req06I1FFormatter i1fFormatter = new Req06I1FFormatter();
	
	public void doReceive(SfSqlConfig config, Job job) {
		File target = new File(job.getAbsolutePath());
		
		String queryString = null;
		QueryResult query = null;
		
		try {
			queryString = masterFormatter.genSfSQL(config);
			logger.info(queryString);
			query = this.soap.query(queryString, this.sh, null, null, null);
			this.handleQuery(query, target, config);
			
			while (!query.getDone()) {
				query = this.soap.queryMore(query.getQueryLocator(), this.sh, null);
				this.handleQuery(query, target, config);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void handleQuery(QueryResult query, File target, SfSqlConfig config) throws Exception {
		String source = null;
		for (SObject so : query.getRecords()) {
			Opportunity master = (Opportunity)so;
			source = masterFormatter.format(master);
			this.write(target, source);
			
			//OpportunityHistories
			if (null != master.getDICaseStatusR()) {
				for (SObject dso : master.getDICaseStatusR().getRecords()) {
					DICaseStatusC detail = (DICaseStatusC) dso;
					i1aFormatter.preFormat(master, detail);
					
					source = i1aFormatter.format(detail);
					this.write(target, source);
				}
			}
			
			//DI_Related_Account__r
			if (null != master.getDIRelatedAccountR()) {
				for (SObject dso : master.getDIRelatedAccountR().getRecords()) {
					DIRelatedAccountC detail = (DIRelatedAccountC) dso;
					if (StringUtils.isNotEmpty(detail.getTargetCustomerIDC())) {
						i1bFormatter.preFormat(master, detail);
					
						source = i1bFormatter.format(detail);
						this.write(target, source);
					}
				}
				
				for (SObject dso : master.getDIRelatedAccountR().getRecords()) {
					DIRelatedAccountC detail = (DIRelatedAccountC) dso;
					if (StringUtils.isEmpty(detail.getTargetCustomerIDC())) {
						i1cFormatter.preFormat(master, detail);
					
						source = i1cFormatter.format(detail);
						this.write(target, source);
					}
				}
			}
			
			//DI_Milestone_History__r
			if (null != master.getKeyMilestonesR()) {
				for (SObject dso : master.getKeyMilestonesR().getRecords()) {
					KeyMilestoneC detail = (KeyMilestoneC) dso;
					i1dFormatter.preFormat(master, detail);
					
					source = i1dFormatter.format(detail);
					this.write(target, source);
				}
			}
			
			//Product_Opportunity__r
			if (null != master.getProductOpportunityR()) {
				for (SObject dso : master.getProductOpportunityR().getRecords()) {
					ProductOpportunityC detail = (ProductOpportunityC) dso;
					i1eFormatter.preFormat(master, detail);
					
					source = i1eFormatter.format(detail);
					this.write(target, source);
					
				}
			}
			//Catch Opportunity_Data__r
			config.setMasterId(master.getId());
			String queryString = i1fFormatter.genSfSQL(config);
			logger.info(queryString);
			QueryResult odsos = this.soap.query(queryString, this.sh, null, null, null);
			for (SObject odso : odsos.getRecords()) {
				OpportunityDataC od = (OpportunityDataC) odso;
				i1fFormatter.preFormat(master, od);
				source = i1fFormatter.format(od);
				this.write(target, source);
			}
			/*
			//Opportunity_Data__r
			if (null != master.getOpportunityDataR()) {
				for (SObject dso : master.getOpportunityDataR().getRecords()) {
					OpportunityDataC detail = (OpportunityDataC) dso;
					i1fFormatter.preFormat(master, detail);
					
					source = i1fFormatter.format(detail);
					FileUtils.write(target, source, true);
				}
			}
			*/
			
		}
	}
	
	@Override
	public void postInit() {
		masterFormatter.init();
		i1aFormatter.init();
		i1bFormatter.init();
		i1cFormatter.init();
		i1dFormatter.init();
		i1eFormatter.init();
		i1fFormatter.init();
		
		masterFormatter.getSubParsers().add(i1aFormatter);
//		masterFormatter.getSubParsers().add(i1bFormatter);
		masterFormatter.getSubParsers().add(i1cFormatter);
		masterFormatter.getSubParsers().add(i1dFormatter);
		masterFormatter.getSubParsers().add(i1eFormatter);
//		masterFormatter.getSubParsers().add(i1fFormatter);
	}
}
