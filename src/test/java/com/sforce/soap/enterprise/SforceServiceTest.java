package com.sforce.soap.enterprise;

import java.util.List;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.soap.enterprise.sobject.CustomerCategoryC;
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
			System.out.println(surl);
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
	public void testReq01() throws Exception {
		DescribeSObjectResult dso = soap.describeSObject("Exchange_Rate__c", sh, null, null);
		for (Field field : dso.getFields()) {
			logger.debug("	"+field.getName());
		}
		
		QueryResult query = soap.query("SELECT Date__c, Currency__c, Exchange_Rate__c, Key_sync__c FROM Exchange_Rate__c ", sh, null, null, null);
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
	public void testReq02() throws Exception {
		//WHERE LastModifiedDate >= 2012-05-21T00:00:00Z
		DescribeSObjectResult dso = soap.describeSObject("EPN_Master__c", sh, null, null);
		for (Field field : dso.getFields()) {
			System.out.println(field.getName());
		}
		/*WHERE LastModifiedDate<2012-06-06T05:00:00Z*/
		/*
		QueryResult query = soap.query("SELECT LastModifiedDate,Group_Line__c,Name,Product_Type__c,Density__c,Voltage__c,Pkg_Type__c,PKG_Name__c,Pin_Count__c,Grade__c,Car_Grade_Flag__c,Brand__c,Body_Size__c,Expired_Date__c,Mark_for_Delete__c,EOL_Flag__c,EOL_Issue_Date__c,LO_Date__c,LS_Date__c FROM EPN_Master__c", sh, null, null, null);
		List<SObject> objects = query.getRecords();
		System.out.println("object size["+objects.size()+"]");
		for (SObject so : objects) {
			System.out.println(so);
		}
		*/
	}
	
	@Test
	public void testReq03() throws Exception {
		//WHERE LastModifiedDate >= 2012-05-21T00:00:00Z
		DescribeSObjectResult dso = soap.describeSObject("EPN_Product_Body_Link__c", sh, null, null);
		for (Field field : dso.getFields()) {
			System.out.println(field.getName());
		}
		/*
		QueryResult query = soap.query("SELECT LastModifiedDate, Name,Product_Body__c,Mask_Opt__c,BE_Opt__c,Release_Status__c,FAB__c,Mark_for_Delete__c FROM EPN_Product_Body_Link__c WHERE LastModifiedDate = THIS_MONTH", sh, null, null, null);
		List<SObject> objects = query.getRecords();
		System.out.println("object size["+objects.size()+"]");
		for (SObject so : objects) {
			System.out.println(so);
		}
		*/
	}
	
	@Test
	public void testReq04() throws Exception {
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
		
		QueryResult query = soap.query("SELECT Id, Status__c, Submit_Date__c, (SELECT Name FROM Competitor_price__c.Competitor_Price_Item__r)  FROM Competitor_price__c as cp", sh, null, null, null);
		List<SObject> objects = query.getRecords();
		System.out.println("object size["+objects.size()+"]");
		for (SObject so : objects) {
			System.out.println(so);
		}
		
	}
	
	@Test
	public void testReq05() throws Exception {
		//WHERE LastModifiedDate >= 2012-05-21T00:00:00Z
		DescribeSObjectResult dso = soap.describeSObject("Opportunity", sh, null, null);
		for (Field field : dso.getFields()) {
			System.out.println("	"+field.getName());
		}
		QueryResult query = soap.query("SELECT StageName,Name,DI_URL__c,Record_Type__c,CreatedDate,Customer_Project_Status__c FROM Opportunity WHERE Id <> null and (Record_Type__c = 'BDI' or Record_Type__c = 'CDI') and CreatedDate >= 2012-04-01T00:00:00+0800", sh, null, null, null);
		List<SObject> objects = query.getRecords();
		for (SObject so : objects) {
			logger.debug("{}",so);
		}
	}
	
	@Test
	public void testReq06() throws Exception {
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
	public void testReq07() throws Exception {
		//WHERE LastModifiedDate >= 2012-05-21T00:00:00Z
		DescribeSObjectResult dso = soap.describeSObject("Product_Opportunity__c", sh, null, null);
		for (Field field : dso.getFields()) {
			System.out.println("	"+field.getName());
		}
		
		QueryResult query = soap.query("SELECT Id,Design_in_Site_ID__c,Prospect_Group_No__c,EPN__c,Application__c,SAM_Avg_Amount__c,First_MP_Order_No__c FROM Product_Opportunity__c WHERE First_MP_Order_No__c <> null and Check_Result__c = null", sh, null, null, null);
		List<SObject> objects = query.getRecords();
		for (SObject so : objects) {
			logger.debug("{}",so);
		}
	}
	
	@Test
	public void testReq08() throws Exception {
		DescribeSObjectResult dso = soap.describeSObject("Product_Opportunity__c", sh, null, null);
		logger.debug("Table[{}]");
		for (Field field : dso.getFields()) {
			System.out.println("	"+field.getName());
		}
		
		QueryResult query = soap.query("SELECT Id,Check_Result__c,Design_in_Site_ID__c FROM Product_Opportunity__c ", sh, null, null, null);
		List<SObject> objects = query.getRecords();
		for (SObject so : objects) {
			logger.debug("{}",so);
		}
		
		
	}
	
	@Test
	public void testReq09() throws Exception {
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
	public void testReq11() throws Exception {
		//WHERE LastModifiedDate >= 2012-05-21T00:00:00Z
		DescribeSObjectResult dso = soap.describeSObject("Account", sh, null, null);
		for (Field field : dso.getFields()) {
			System.out.println("	"+field.getName());
		}
		
		QueryResult query = soap.query("SELECT AccountNumber, RecordTypeId, Record_Type__c, Account_Group__c FROM Account WHERE Record_Type__c = 'SAP_Customer'", sh, null, null, null);
		List<SObject> objects = query.getRecords();
		for (SObject so : objects) {
			logger.debug("{}",so);
		}
		
	}
	
	@Test
	public void testReq12() throws Exception {
		//WHERE LastModifiedDate >= 2012-05-21T00:00:00Z
		DescribeSObjectResult dso = soap.describeSObject("Account", sh, null, null);
		for (Field field : dso.getFields()) {
			System.out.println("	"+field.getName());
		}
		
		QueryResult query = soap.query("SELECT AccountNumber, Record_Type__c, Account_Group__c FROM Account WHERE Record_Type__c = 'SAP_Customer'", sh, null, null, null);
		List<SObject> objects = query.getRecords();
		for (SObject so : objects) {
			logger.debug("{}",so);
		}
		
	}
	
	@Test
	public void testReq13() throws Exception {
		//WHERE LastModifiedDate >= 2012-05-21T00:00:00Z
		DescribeSObjectResult dso = soap.describeSObject("Customer_Category__c", sh, null, null);
		System.out.println("Table Customer_Category__c");
		for (Field field : dso.getFields()) {
			System.out.println("	"+field.getName());
		}
//		CustomerCategoryC
		QueryResult query = soap.query("SELECT Account_Number__c, GLOBAL_ACCT__c, HIGH_DEN_ACCT__c, BASELINE_ACCT__c, AEB_P1_ACCT__c, AEB_P2_ACCT__c FROM Customer_Category__c ", sh, null, null, null);
		List<SObject> objects = query.getRecords();
		for (SObject so : objects) {
			logger.debug("{}",so);
		}
		
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
		/*
		dso = soap.describeSObject("Custom_Site_Attendee__c", sh, null, null);
		System.out.println("Custom_Site_Attendee__c");
		for (Field field : dso.getFields()) {
			System.out.println("	"+field.getName());
		}
		
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
