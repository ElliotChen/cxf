package com.sforce.intf.impl;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class BaseComponentTest {
	private static final Logger logger = LoggerFactory.getLogger(BaseComponentTest.class);
	@Autowired
	private TaskExecutor taskExecutor;
	
	@Autowired
	@Qualifier("mockComponent")
	private BaseComponent mockComponent;
	@Autowired
	@Qualifier("req01")
	private BaseComponent baseComponent;
	
	@Ignore
	@Test
	public void testMock() {
		logger.debug("Test Start!");
		for (int i =0; i < 5; i++) {
			try {
				Thread.sleep(10*1000);
				taskExecutor.execute(mockComponent);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void teset() {
		logger.debug("Test Start!");
		baseComponent.run();
		logger.debug("Test End!");
	}
}
