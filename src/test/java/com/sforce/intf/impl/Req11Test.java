package com.sforce.intf.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class Req11Test {
	private static final Logger logger = LoggerFactory.getLogger(Req11Test.class);

	@Autowired
	@Qualifier("req11")
	private BaseComponent baseComponent;

	@Test
	public void teset() {
		logger.debug("Test Start!");
		baseComponent.run();
		logger.debug("Test End!");
	}
}
