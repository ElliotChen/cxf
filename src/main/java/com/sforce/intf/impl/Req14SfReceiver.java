package com.sforce.intf.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.domain.Job;
import com.sforce.parser.Parser;
import com.sforce.parser.Req14TripFormatter;
import com.sforce.parser.Req14VisitFormatter;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.soap.enterprise.sobject.TripReportC;
import com.sforce.soap.enterprise.sobject.VisitReportC;
import com.sforce.to.SfSqlConfig;

public class Req14SfReceiver extends SfReceiver {
	private static final Logger logger = LoggerFactory.getLogger(Req14SfReceiver.class);
	protected List<Parser<?>> parsers;
	
	private Req14TripFormatter tripFormatter = new Req14TripFormatter();
	private Req14VisitFormatter visitFormatter = new Req14VisitFormatter();
	public void doReceive(SfSqlConfig config, Job job) {
		File target = new File(job.getAbsolutePath());
		
		String queryString = null;
		QueryResult query = null;
		String source = null;

		try {
			queryString = tripFormatter.genSfSQL(config);
			logger.info(queryString);
			query = this.soap.query(queryString, this.sh, null, null, null);
			for (SObject so : query.getRecords()) {
				source = tripFormatter.format((TripReportC)so);
				FileUtils.write(target, source, true);
			}
			
			queryString = visitFormatter.genSfSQL(config);
			logger.info(queryString);
			query = this.soap.query(queryString, this.sh, null, null, null);
			for (SObject so : query.getRecords()) {
				VisitReportC vr = (VisitReportC) so;
				//this.preFormat(vr);
				source = visitFormatter.format(vr);
				FileUtils.write(target, source, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void postInit() {
		tripFormatter.init();
		visitFormatter.init();
		
//		visitFormatter.getSubParsers().add(cusFormatter);
	}
	/*
	protected void preFormat(VisitReportC vr) {
		if (null == vr) {
			return;
		}
		logger.debug(vr.toString());
		StringBuilder sb = new StringBuilder();
		if(StringUtils.isNotEmpty(vr.getProductEPN1C())) {
			sb.append(vr.getProductEPN1C()+";");
		}
		if(StringUtils.isNotEmpty(vr.getProductEPN2C())) {
			sb.append(vr.getProductEPN2C()+";");
		}
		if(StringUtils.isNotEmpty(vr.getProductEPN3C())) {
			sb.append(vr.getProductEPN3C()+";");
		}
		if(StringUtils.isNotEmpty(vr.getProductEPN4C())) {
			sb.append(vr.getProductEPN4C()+";");
		}
		if(StringUtils.isNotEmpty(vr.getProductEPN5C())) {
			sb.append(vr.getProductEPN5C()+";");
		}
		vr.setProductEPN1C(sb.toString());
		
		if (null != vr.getCustomerR()) {
			vr.setProductEPN2C(vr.getCustomerR().getId());
		}
		
		if (null != vr.getRecordType()) {
			vr.setRecordTypeId(vr.getRecordType().getName());
		}
	}
	*/
}
