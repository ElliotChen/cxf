package com.sforce.intf.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ctc.wstx.util.StringUtil;
import com.sforce.domain.Job;
import com.sforce.intf.Sender;
import com.sforce.parser.Parser;
import com.sforce.parser.Req01MasterParser;
import com.sforce.service.JobManager;
import com.sforce.soap.enterprise.sobject.ExchangeRateC;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.util.DateUtils;

public class Req01SfSender extends SfConnector implements Sender {
	private static final Logger logger = LoggerFactory.getLogger(Req01SfSender.class);
	@Autowired
	protected JobManager jobManager;
	private List<Parser> parsers;
	private List<SObject> objs = new ArrayList<SObject>();
	@Override
	public boolean send() {
		this.connect();
		
		if (!this.connected) {
			logger.warn("Doesn't connecto to SalesForce, Please run connect first.");
			return false;
		}
		/*
		Req01MasterParser fp = new Req01MasterParser();
		fp.init();
		*/
		List<Job> jobs = new ArrayList<Job>();
		Job job = null;
		while ((job = jobManager.occupyFirstJob(component)) != null) {
			jobs.add(job);
			logger.info("Find Sending : {}", job);
			File source = new File(job.getAbsolutePath());
			try {
				List<String> lines = FileUtils.readLines(source);
				for (String s : lines) {
					String[] split = s.split("\\t");
					ExchangeRateC target = null;
					for (Parser parser : this.parsers) {
						if (parser.accept(split)) {
							target = (ExchangeRateC) parser.parse(split);
							break;
						}
					}
					if (null == target) {
						logger.error("Unknown source[{}]",s);
						continue;
					}
					if (null != target.getDateC() && StringUtils.isNotEmpty(target.getCurrencyC())) {
						target.setKeySyncC(DateUtils.formatDate(target.getDateC())+target.getCurrencyC());
						objs.add(target);
					} else {
						logger.error("Key column date[{}], currency[{}] can't be empty", target.getDateC(), target.getCurrencyC());
					}
					logger.debug("Find Source [{}]", target);
				}
				if (objs.isEmpty()) {
					logger.error("Parsing file[{}] but can't find any SObject", job.getAbsolutePath());
					continue;
				}
				if (debugMode) {
					logger.debug("Send to SalesForce :");
					for (SObject so : objs) {
						logger.debug("---- {}",so);
					}
				} else {
					soap.upsert("Key_sync__c", objs , sh, null, null, null, null, null, null, null, null, null, null);
					this.jobManager.finish(job);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (debugMode) {
			for (Job j : jobs) {
				this.jobManager.release(j);
			}
		}
		this.disconnect();
		return true;
	}

	public void init(InitConfig config) {
		this.component = config.getName();
		this.debugMode = config.getDebugMode();
		if (null != this.parsers) {
			for (Parser<?> parser : this.parsers) {
				parser.init();
			}
		}
	}

	public List<Parser> getParsers() {
		return parsers;
	}

	public void setParsers(List<Parser> parsers) {
		this.parsers = parsers;
	}
}
