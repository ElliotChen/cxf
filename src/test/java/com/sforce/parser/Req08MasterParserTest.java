package com.sforce.parser;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.soap.enterprise.sobject.EPNProductBodyLinkC;
import com.sforce.soap.enterprise.sobject.ExchangeRateC;
import com.sforce.soap.enterprise.sobject.SObject;

/**
 * com.sforce.soap.enterprise.sobject.EPNProductBodyLinkC@556fb175[attachments=<null>,beOptC=T,createdBy=<null>,createdById=<null>,createdDate=<null>,fabc=FAB2,feedSubscriptionsForEntity=<null>,isDeleted=<null>,keyC=<null>,lastModifiedBy=<null>,lastModifiedById=<null>,lastModifiedDate=<null>,markForDeleteC=D,maskOptC=T,name=MX23L1613XI-12 / 5119,notes=<null>,notesAndAttachments=<null>,owner=<null>,ownerId=<null>,processInstances=<null>,processSteps=<null>,productBodyC=5119,releaseStatusC=<null>,systemModstamp=<null>,visitReport2DelR=<null>,visitReport3DelR=<null>,visitReport4DelR=<null>,visitReport5R=<null>,visitReportDelR=<null>,fieldsToNull=<null>,id=<null>]
 * com.sforce.soap.enterprise.sobject.EPNProductBodyLinkC@17eba425[attachments=<null>,beOptC=V,createdBy=<null>,createdById=<null>,createdDate=<null>,fabc=FAB2,feedSubscriptionsForEntity=<null>,isDeleted=<null>,keyC=<null>,lastModifiedBy=<null>,lastModifiedById=<null>,lastModifiedDate=<null>,markForDeleteC=<null>,maskOptC=V,name=MX23L1613XI-12 / 5124,notes=<null>,notesAndAttachments=<null>,owner=<null>,ownerId=<null>,processInstances=<null>,processSteps=<null>,productBodyC=5124,releaseStatusC=<null>,systemModstamp=<null>,visitReport2DelR=<null>,visitReport3DelR=<null>,visitReport4DelR=<null>,visitReport5R=<null>,visitReportDelR=<null>,fieldsToNull=<null>,id=<null>]
 * @author elliot
 *
 */
public class Req08MasterParserTest {
	private static final Logger logger = LoggerFactory.getLogger(Req08MasterParserTest.class);
	@Test
	public void testParse() {
		try {
			List<String> lines = FileUtils.readLines(new File("/Users/elliot/gitrepo/cxf/src/test/resources/req08.txt"));
			for (String s: lines) {
				System.out.println(s);
				String[] split = s.split("\\t");
				for (int i=0; i < split.length; i++) {
					logger.debug("{} : [{}]", i+1, split[i]);
				}
				Req08MasterParser fp = new Req08MasterParser();
				fp.init();
				SObject target = fp.parse(split);
				logger.debug("Find Source [{}]",target);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testGenSQL() {
		Req08MasterParser fp = new Req08MasterParser();
		fp.listColumnInfo();
		logger.debug(fp.genSQLColumn());
	}
}
