package com.sforce.intf.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sforce.domain.Job;
import com.sforce.intf.Sender;
import com.sforce.parser.Parser;
import com.sforce.service.JobManager;
import com.sforce.soap.enterprise.Error;
import com.sforce.soap.enterprise.UpsertResult;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.to.InitConfig;
import com.sforce.util.CollectionUtils;

public class SfSender extends SfConnector implements Sender {
	private static final Logger logger = LoggerFactory.getLogger(SfSender.class);
	@Autowired
	protected JobManager jobManager;
	private List<Parser<?>> parsers;
	private List<SObject> objs = new ArrayList<SObject>();
	private String syncKey;
	private String[] receivers;
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
			List<String> errors = new ArrayList<String>();
			jobs.add(job);
			logger.info("Find Job for Sending : {}", job);
			try {
				logger.debug("Load File as source");
				File source = new File(job.getAbsolutePath());
				logger.debug("Read File Lines by FileUtils.readLines");
				BufferedReader br
				   = new BufferedReader(new FileReader(source));
//				List<String> lines = FileUtils.readLines(source);
				List<String> lines = new ArrayList<String>();
				String sline = null;
				while ((sline = br.readLine()) != null) {
					lines.add(sline);
				}
				logger.debug("Split file to String[]");
				for (String s : lines) {
					String[] split = StringUtils.splitByWholeSeparatorPreserveAllTokens(s, "\t");
					SObject target = null;
					Parser parser = null;
					for (Parser p : this.parsers) {
						if (p.accept(split)) {
							parser = p;
							break;
						}
					}
					if (null == parser) {
						logger.error("Unknown source[{}]",s);
						continue;
					}
					target = parser.parse(split);
					if (null == target) {
						logger.error("Unknown source[{}]",s);
						continue;
					}
					
					objs.add(target);
					//logger.debug("Find Source [{}]", target);
				}
				if (objs.isEmpty()) {
					logger.error("Parsing file[{}] but can't find any SObject", job.getAbsolutePath());
					continue;
				}
				if (debugMode) {
					logger.debug("[DEBUG MODE] Send to SalesForce :");
					for (SObject so : objs) {
						logger.debug("---- {}",so);
					}
				} else {
					int pageSize = 200;
					List<List<SObject>> lists = CollectionUtils.splitList(objs, pageSize);
					
					int loop = 0;
					for (List<SObject> sos : lists) {
						logger.debug("Loop[{}] and Current Size[{}]", loop++, sos.size());
						List<UpsertResult> result = soap.upsert(syncKey, sos , sh, null, null, null, null, null, null, null, null, null, null);
						
						for (int rindex = 0; rindex < result.size(); rindex++) {
							UpsertResult ur = result.get(rindex);
							if (!ur.getSuccess()) {
								String line = lines.get(loop*pageSize+rindex);
								errors.add("Upsert SObject Failed : Source should be ["+line+"]");
								logger.error("[{}]-[{}] Upsert SObject Failed : Key[{}]", new Object[] {job.getComponent(), job.getMqId(), line});
								for (Error err : ur.getErrors()) {
									errors.add("Code["+err.getStatusCode()+"] - Message:"+err.getMessage());
									logger.error("[{}]-[{}] Code [{}] - Message: {}",new Object[] {job.getComponent(), job.getMqId(),err.getStatusCode(), err.getMessage()});
								}
							}
						}
					}
					
					this.jobManager.finish(job, errors, receivers);
				}
			} catch (Exception e) {
				logger.error("Send data to SalesForce Failed.", e);
				
				errors.add("Encounter a exception:"+e.getMessage());
				errors.add("Please check error log for more details.");
				this.jobManager.abandon(job, errors, receivers);
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

	private Logger getInterfaceLogger(String component, String messageId) {
		return LoggerFactory.getLogger(component);
	}
	public List<Parser<?>> getParsers() {
		return parsers;
	}

	public void setParsers(List<Parser<?>> parsers) {
		this.parsers = parsers;
	}

	public String getSyncKey() {
		return syncKey;
	}

	public void setSyncKey(String syncKey) {
		this.syncKey = syncKey;
	}

	public JobManager getJobManager() {
		return jobManager;
	}

	public void setJobManager(JobManager jobManager) {
		this.jobManager = jobManager;
	}

	public String[] getReceivers() {
		return receivers;
	}

	public void setReceivers(String[] receivers) {
		this.receivers = receivers;
	}
	
	
}
