package com.sforce.intf.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.domain.Job;
import com.sforce.parser.Parser;
import com.sforce.parser.Req15I1AFormatter;
import com.sforce.parser.Req15I1BFormatter;
import com.sforce.parser.Req15I1CFormatter;
import com.sforce.parser.Req15MasterFormatter;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.sobject.MacronixSiteAttendeeC;
import com.sforce.soap.enterprise.sobject.OtherRelatedGroupC;
import com.sforce.soap.enterprise.sobject.RelatedApplicationC;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.soap.enterprise.sobject.VisitReportC;
import com.sforce.to.SfSqlConfig;

public class Req15SfReceiver extends SfReceiver {
	private static final Logger logger = LoggerFactory.getLogger(Req15SfReceiver.class);
	protected List<Parser<?>> parsers;
	
	private Req15MasterFormatter masterFormatter = new Req15MasterFormatter();
	private Req15I1AFormatter i1aFormatter = new Req15I1AFormatter();
	private Req15I1BFormatter i1bFormatter = new Req15I1BFormatter();
	private Req15I1CFormatter i1cFormatter = new Req15I1CFormatter();
	
	public void doReceive(SfSqlConfig config, Job job) {
		File target = new File(job.getAbsolutePath());
		
		String queryString = null;
		QueryResult query = null;
		String source = null;

		try {
			queryString = masterFormatter.genSfSQL(config);
			logger.info(queryString);
			query = this.soap.query(queryString, this.sh, null, null, null);
			for (SObject so : query.getRecords()) {
				VisitReportC vr = (VisitReportC)so;
				source = masterFormatter.format(vr);
				this.write(target, source);
				
				//Macronix_Site_Attendee__r
				if (null != vr.getMacronixSiteAttendeeR()) {
					for (SObject i1a : vr.getMacronixSiteAttendeeR().getRecords()) {
						MacronixSiteAttendeeC msa = (MacronixSiteAttendeeC) i1a;
						i1aFormatter.preFormat(vr, msa);
						
						source = i1aFormatter.format(msa);
						this.write(target, source);
					}
				}
				
				//Other_Related_Group__r
				if (null != vr.getOtherRelatedGroupR()) {
					for (SObject i1a : vr.getOtherRelatedGroupR().getRecords()) {
						OtherRelatedGroupC msa = (OtherRelatedGroupC) i1a;
						i1bFormatter.preFormat(vr, msa);
						
						source = i1bFormatter.format(msa);
						this.write(target, source);
					}
				}
				
				//Related_Applications__r
				if (null != vr.getRelatedApplicationsR()) {
					for (SObject i1a : vr.getRelatedApplicationsR().getRecords()) {
						RelatedApplicationC msa = (RelatedApplicationC) i1a;
						i1cFormatter.preFormat(vr, msa);
						
						source = i1cFormatter.format(msa);
						this.write(target, source);
					}
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void postInit() {
		masterFormatter.init();
		i1aFormatter.init();
		i1bFormatter.init();
		i1cFormatter.init();
		
		masterFormatter.getSubParsers().add(i1aFormatter);
		masterFormatter.getSubParsers().add(i1bFormatter);
		masterFormatter.getSubParsers().add(i1cFormatter);
	}
}
