package com.sforce.intf.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sforce.domain.Job;
import com.sforce.intf.Sender;
import com.sforce.parser.Parser;
import com.sforce.parser.Req01MasterParser;
import com.sforce.service.JobManager;
import com.sforce.soap.enterprise.sobject.ExchangeRateC;
import com.sforce.soap.enterprise.sobject.SObject;

public class SfSender extends SfConnector implements Sender {
	private static final Logger logger = LoggerFactory.getLogger(SfSender.class);
	@Autowired
	protected JobManager jobManager;
	private List<Parser> parsers;
	private List<SObject> objs = new ArrayList<SObject>();
	@Override
	public boolean send() {
		if (!this.connected) {
			return false;
		}
		
		Req01MasterParser fp = new Req01MasterParser();
		fp.init();
		
		Job job = null;
		while ((job = jobManager.occupyFirstJob(component)) != null) {
			logger.info("Find Sending : {}", job);
			File source = new File(job.getAbsolutePath());
			try {
				List<String> lines = FileUtils.readLines(source);
				for (String s : lines) {
					String[] split = s.split("\\t");
					ExchangeRateC target = fp.parse(split);
					objs.add(target);
					logger.debug("Find Source [{}]", target);
				}
				if (objs.isEmpty()) {
					logger.error("Parsing file[{}] but can't find any SObject", job.getAbsolutePath());
					continue;
				}
				soap.upsert("synkey", objs , sh, null, null, null, null, null, null, null, null, null, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}

}
