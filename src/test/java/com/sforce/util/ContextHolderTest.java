package com.sforce.util;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sforce.intf.Component;
import com.sforce.parser.Req08MasterParserTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class ContextHolderTest {
	private static final Logger logger = LoggerFactory.getLogger(ContextHolderTest.class);
	@Test
	public void test() {
		List<Component> list = ContextHolder.getAllConComponents();
		logger.info("Component size[{}]", list.size());
	}

}
