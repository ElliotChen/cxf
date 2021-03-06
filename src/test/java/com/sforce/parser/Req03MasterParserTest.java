package com.sforce.parser;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.soap.enterprise.sobject.EPNProductBodyLinkC;
import com.sforce.soap.enterprise.sobject.ExchangeRateC;

/**
 * com.sforce.soap.enterprise.sobject.EPNProductBodyLinkC@556fb175[attachments=<null>,beOptC=T,createdBy=<null>,createdById=<null>,createdDate=<null>,fabc=FAB2,feedSubscriptionsForEntity=<null>,isDeleted=<null>,keyC=<null>,lastModifiedBy=<null>,lastModifiedById=<null>,lastModifiedDate=<null>,markForDeleteC=D,maskOptC=T,name=MX23L1613XI-12 / 5119,notes=<null>,notesAndAttachments=<null>,owner=<null>,ownerId=<null>,processInstances=<null>,processSteps=<null>,productBodyC=5119,releaseStatusC=<null>,systemModstamp=<null>,visitReport2DelR=<null>,visitReport3DelR=<null>,visitReport4DelR=<null>,visitReport5R=<null>,visitReportDelR=<null>,fieldsToNull=<null>,id=<null>]
 * com.sforce.soap.enterprise.sobject.EPNProductBodyLinkC@17eba425[attachments=<null>,beOptC=V,createdBy=<null>,createdById=<null>,createdDate=<null>,fabc=FAB2,feedSubscriptionsForEntity=<null>,isDeleted=<null>,keyC=<null>,lastModifiedBy=<null>,lastModifiedById=<null>,lastModifiedDate=<null>,markForDeleteC=<null>,maskOptC=V,name=MX23L1613XI-12 / 5124,notes=<null>,notesAndAttachments=<null>,owner=<null>,ownerId=<null>,processInstances=<null>,processSteps=<null>,productBodyC=5124,releaseStatusC=<null>,systemModstamp=<null>,visitReport2DelR=<null>,visitReport3DelR=<null>,visitReport4DelR=<null>,visitReport5R=<null>,visitReportDelR=<null>,fieldsToNull=<null>,id=<null>]
 * @author elliot
 *
 */
public class Req03MasterParserTest {
	private static final Logger logger = LoggerFactory.getLogger(Req03MasterParserTest.class);
	Req03MasterParser fp = new Req03MasterParser();
	@Test
	public void testParse() {
		try {
//			List<String> lines = FileUtils.readLines(new File("/Users/elliot/gitrepo/cxf/src/test/resources/req03.txt"));
			List<String> lines = FileUtils.readLines(new File("/Users/elliot/mqfile/test/req03_mxic_light.txt"));
			for (String s: lines) {
				String[] split = StringUtils.splitByWholeSeparatorPreserveAllTokens(s, "\t");
				fp.analysis(split);
				EPNProductBodyLinkC target = fp.parse(split);
				logger.debug("Find Source [{}]",target);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testGenSQL() {
		fp.listColumnInfo();
		logger.debug(fp.genSQLColumn());
	}
}
