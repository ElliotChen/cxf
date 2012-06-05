package com.sforce.soap.enterprise;

import java.util.List;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.soap.enterprise.sobject.EPNProductBodyLinkC;
import com.sforce.soap.enterprise.sobject.SObject;

public class SforceServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(SforceServiceTest.class);
//	public static final Log
	private static Soap soap;
	private static SessionHeader sh;
	@BeforeClass
	public static void before() {
		SforceService sf = new SforceService();
		soap = sf.getSoap();
		
		LoginScopeHeader lsh = new LoginScopeHeader();
		LoginResult login = null;
		try {
			logger.info("Test Begin:"+System.currentTimeMillis());
			login = soap.login("fiti02@mxic.com.tw.uat", "t25146875", lsh);
			String surl = login.getServerUrl();
			sh = new SessionHeader();
			sh.setSessionId(login.getSessionId());
			Map<String, Object> requestContext = ((BindingProvider)soap).getRequestContext();
			requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, surl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public static void after() {
		try {
			soap.logout(sh);
			logger.info("Test End:"+System.currentTimeMillis());
		} catch (UnexpectedErrorFault e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTables() throws Exception {
		List<DescribeTabSetResult> dt = soap.describeTabs(sh, null);
		for (DescribeTabSetResult ds : dt) {
			System.out.println(ds.getLabel());
		}
		DescribeGlobalResult dg = soap.describeGlobal(sh, null);
		List<DescribeGlobalSObjectResult> sobjects = dg.getSobjects();
		for (DescribeGlobalSObjectResult dgs : sobjects) {
			System.out.println(dgs.getName());
		}
	}
	
	@Test
	public void testSearchContact() throws Exception {
		QueryResult query = soap.query("SELECT Id, AccountId, Email FROM Contact", sh, null, null, null);
		for (SObject sob : query.getRecords()) {
			System.out.println("Contact Id is : "+sob.getId());
		}
	}
	
	
	@Test
	public void testReq1() throws Exception {
		DescribeSObjectResult dso = soap.describeSObject("Exchange_Rate__c", sh, null, null);
		for (Field field : dso.getFields()) {
			logger.debug("	"+field.getName());
		}
		
		QueryResult query = soap.query("SELECT Date__c, Currency__c, Exchange_Rate__c FROM Exchange_Rate__c ", sh, null, null, null);
		List<SObject> objects = query.getRecords();
		for (SObject so : objects) {
			logger.debug("{}",so);
		}
		/*
		query = soap.query("FROM Exchange_Rate__c WHERE CreatedDate > YESTERDAY", sh, null, null, null);
		objects = query.getRecords();
		for (SObject so : objects) {
			System.out.println(so);
		}
		*/
	}
	
	@Test
	public void testReq2() throws Exception {
		//WHERE LastModifiedDate >= 2012-05-21T00:00:00Z
		DescribeSObjectResult dso = soap.describeSObject("EPN_Master__c", sh, null, null);
		for (Field field : dso.getFields()) {
			System.out.println("	"+field.getName());
		}
		QueryResult query = soap.query("SELECT AccountId, Email, LastModifiedDate FROM EPN_Master__c", sh, null, null, null);
		List<SObject> objects = query.getRecords();
		System.out.println("object size["+objects.size()+"]");
		for (SObject so : objects) {
			System.out.println(so);
		}
	}
	
	@Test
	public void testReq3() throws Exception {
		//WHERE LastModifiedDate >= 2012-05-21T00:00:00Z
		DescribeSObjectResult dso = soap.describeSObject("EPN_Product_Body_Link__c", sh, null, null);
		for (Field field : dso.getFields()) {
			System.out.println("	"+field.getName());
		}
		/*
		QueryResult query = soap.query("SELECT AccountId, Email, LastModifiedDate FROM EPN_Product_Body_Link__c", sh, null, null, null);
		List<SObject> objects = query.getRecords();
		System.out.println("object size["+objects.size()+"]");
		for (SObject so : objects) {
			System.out.println(so);
		}
		*/
		
	}
	
	@Test
	public void testReq4() throws Exception {
		//WHERE LastModifiedDate >= 2012-05-21T00:00:00Z
		DescribeSObjectResult dso = soap.describeSObject("Competitor_price__c", sh, null, null);
		System.out.println("Table Competitor_price__c");
		for (Field field : dso.getFields()) {
			System.out.println("	"+field.getName());
		}
		System.out.println("Table Competitor_Price_Item__c");
		dso = soap.describeSObject("Competitor_Price_Item__c", sh, null, null);
		for (Field field : dso.getFields()) {
			System.out.println("	"+field.getName());
		}
		/*
		QueryResult query = soap.query("SELECT AccountId, Email, LastModifiedDate FROM EPN_Product_Body_Link__c", sh, null, null, null);
		List<SObject> objects = query.getRecords();
		System.out.println("object size["+objects.size()+"]");
		for (SObject so : objects) {
			System.out.println(so);
		}
		*/
	}
	
	@Test
	public void testReq5() throws Exception {
		//WHERE LastModifiedDate >= 2012-05-21T00:00:00Z
		DescribeSObjectResult dso = soap.describeSObject("DI__c", sh, null, null);
		for (Field field : dso.getFields()) {
			System.out.println("	"+field.getName());
		}
		/*
		QueryResult query = conn.query("SELECT AccountId, Email, LastModifiedDate FROM EPN_Master__c");
		SObject[] objects = query.getRecords();
		System.out.println("object size["+objects.length+"]");
		for (SObject so : objects) {
			System.out.println(so);
		}
		*/
	}
	
	@Test
	public void testReq6() throws Exception {
		//WHERE LastModifiedDate >= 2012-05-21T00:00:00Z
		DescribeSObjectResult dso = soap.describeSObject("Opportunity_Data__c", sh, null, null);
		System.out.println("Table Opportunity_Data__c");
		for (Field field : dso.getFields()) {
			System.out.println("	"+field.getName());
		}
		/*
		QueryResult query = conn.query("SELECT AccountId, Email, LastModifiedDate FROM EPN_Master__c");
		SObject[] objects = query.getRecords();
		System.out.println("object size["+objects.length+"]");
		for (SObject so : objects) {
			System.out.println(so);
		}
		*/
	}
	
	@Test
	public void testReq7And8() throws Exception {
		//WHERE LastModifiedDate >= 2012-05-21T00:00:00Z
		DescribeSObjectResult dso = soap.describeSObject("Product_Opportunity__c", sh, null, null);
		for (Field field : dso.getFields()) {
			System.out.println("	"+field.getName());
		}
		/*
		QueryResult query = conn.query("SELECT AccountId, Email, LastModifiedDate FROM EPN_Master__c");
		SObject[] objects = query.getRecords();
		System.out.println("object size["+objects.length+"]");
		for (SObject so : objects) {
			System.out.println(so);
		}
		*/
	}
	
	@Test
	public void testReq9() throws Exception {
		//WHERE LastModifiedDate >= 2012-05-21T00:00:00Z
		DescribeSObjectResult dso = soap.describeSObject("Application__c", sh, null, null);
		System.out.println("Table Application__c");
		for (Field field : dso.getFields()) {
			System.out.println("	"+field.getName());
		}
		/*
		QueryResult query = conn.query("SELECT AccountId, Email, LastModifiedDate FROM EPN_Master__c");
		SObject[] objects = query.getRecords();
		System.out.println("object size["+objects.length+"]");
		for (SObject so : objects) {
			System.out.println(so);
		}
		*/
	}
	
	@Test
	public void testReq10() throws Exception {
		//WHERE LastModifiedDate >= 2012-05-21T00:00:00Z
		DescribeSObjectResult dso = soap.describeSObject("Account", sh, null, null);
		System.out.println("Table Account");
		for (Field field : dso.getFields()) {
			System.out.println("	"+field.getName());
		}
		/*
		QueryResult query = conn.query("SELECT AccountId, Email, LastModifiedDate FROM EPN_Master__c");
		SObject[] objects = query.getRecords();
		System.out.println("object size["+objects.length+"]");
		for (SObject so : objects) {
			System.out.println(so);
		}
		*/
	}
	
	@Test
	public void testReq1３() throws Exception {
		//WHERE LastModifiedDate >= 2012-05-21T00:00:00Z
		DescribeSObjectResult dso = soap.describeSObject("Customer_Category__c", sh, null, null);
		System.out.println("Table Customer_Category__c");
		for (Field field : dso.getFields()) {
			System.out.println("	"+field.getName());
		}
		/*
		QueryResult query = conn.query("SELECT AccountId, Email, LastModifiedDate FROM EPN_Master__c");
		SObject[] objects = query.getRecords();
		System.out.println("object size["+objects.length+"]");
		for (SObject so : objects) {
			System.out.println(so);
		}
		*/
	}
	
	@Test
	public void testReq14() throws Exception {
		//WHERE LastModifiedDate >= 2012-05-21T00:00:00Z
		DescribeSObjectResult dso = soap.describeSObject("Trip_Report__c", sh, null, null);
		System.out.println("Trip_Report__c");
		for (Field field : dso.getFields()) {
			System.out.println("	"+field.getName());
		}
		dso = soap.describeSObject("Visit_Report__c", sh, null, null);
		System.out.println("Visit_Report__c");
		for (Field field : dso.getFields()) {
			System.out.println("	"+field.getName());
		}
		dso = soap.describeSObject("Custom_Site_Attendee__c", sh, null, null);
		System.out.println("Custom_Site_Attendee__c");
		for (Field field : dso.getFields()) {
			System.out.println("	"+field.getName());
		}
		/*
		QueryResult query = conn.query("SELECT AccountId, Email, LastModifiedDate FROM EPN_Master__c");
		SObject[] objects = query.getRecords();
		System.out.println("object size["+objects.length+"]");
		for (SObject so : objects) {
			System.out.println(so);
		}
		*/
	}
	
	@Test
	public void testReq15() throws Exception {
		//WHERE LastModifiedDate >= 2012-05-21T00:00:00Z
		DescribeSObjectResult dso = soap.describeSObject("Visit_Report__c", sh, null, null);
		System.out.println("Table Visit_Report__c");
		for (Field field : dso.getFields()) {
			System.out.println("	"+field.getName());
		}
		
		 
		dso = soap.describeSObject("Macronix_Site_Attendee__c", sh, null, null);
		System.out.println("Table Macronix_Site_Attendee__c");
		for (Field field : dso.getFields()) {
			System.out.println("	"+field.getName());
		}
		/*
		QueryResult query = conn.query("SELECT AccountId, Email, LastModifiedDate FROM EPN_Master__c");
		SObject[] objects = query.getRecords();
		System.out.println("object size["+objects.length+"]");
		for (SObject so : objects) {
			System.out.println(so);
		}
		*/
	}

}
