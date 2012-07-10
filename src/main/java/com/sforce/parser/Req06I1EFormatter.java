package com.sforce.parser;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.column.Column;
import com.sforce.column.DateColumn;
import com.sforce.column.DoubleColumn;
import com.sforce.column.FakeColumn;
import com.sforce.column.StringColumn;
import com.sforce.soap.enterprise.sobject.Opportunity;
import com.sforce.soap.enterprise.sobject.ProductOpportunityC;
import com.sforce.to.SfSqlConfig;
/**
 * 
 * @author elliot
 *
 */
public class Req06I1EFormatter extends SubParser<ProductOpportunityC, Opportunity> {
	private static final Logger logger = LoggerFactory.getLogger(Req06I1EFormatter.class);
	
	@Override
	public boolean accept(String[] source) {
		return 2 == source.length;
	}

	@Override
	public Logger getLogger() {
		return logger;
	}

	@Override
	protected void initDefaultColumns() {
		this.columns = new ArrayList<Column<?>>();
		int i = 0;
		columns.add(new FakeColumn(i++, "I1E", ""));
		//43,3,2,5,5,36,10
				for (int index = 0; index < 43; index++) {
					columns.add(new FakeColumn(i++, "", ""));
				}
				for (int index = 0; index < 3; index++) {
					columns.add(new FakeColumn(i++, "", ""));
				}
				for (int index = 0; index < 2; index++) {
					columns.add(new FakeColumn(i++, "", ""));
				}
				for (int index = 0; index < 5; index++) {
					columns.add(new FakeColumn(i++, "", ""));
				}
				for (int index = 0; index < 5; index++) {
					columns.add(new FakeColumn(i++, "", ""));
				}
				
		columns.add(new StringColumn(i++, "id", "DI__c"));//cheat, for detail - OpportunityDataC
		columns.add(new StringColumn(i++, "productTypeC", "Product_Type__c"));
		columns.add(new StringColumn(i++, "densityC", "Density__c"));
		columns.add(new StringColumn(i++, "EPNNameC", "EPN_Name__c"));
		columns.add(new StringColumn(i++, "techUmC", "Tech_um__c"));
		columns.add(new StringColumn(i++, "gradeC", "Grade__c"));
		columns.add(new StringColumn(i++, "referenceEPNC", "Reference_EPN__c"));
		columns.add(new StringColumn(i++, "customerPartNoC", "Customer_Part_No__c"));
		columns.add(new StringColumn(i++, "sampledC", "Sampled__c"));
		columns.add(new DateColumn(i++, "sampleRequestDateC", "Sample_Request_Date__c"));
		
		columns.add(new DateColumn(i++, "sampleShippedDateC", "Sample_Shipped_Date__c"));
		columns.add(new StringColumn(i++, "SAPSampleOrderNoC", "SAP_Sample_Order_No__c"));
		columns.add(new DoubleColumn(i++, "sampleQtyC", "Sample_Qty__c"));
		columns.add(new StringColumn(i++, "qualificationStatusC", "Qualification_Status__c"));
		columns.add(new StringColumn(i++, "failedReasonC", "Failed_Reason__c"));
		columns.add(new StringColumn(i++, "ISMacronixTheSingleSourceC", "IS_Macronix_the_single_source__c"));
		columns.add(new DateColumn(i++, "MPDateAtCustomerC", "MP_Date_at_Customer__c"));
		columns.add(new StringColumn(i++, "firstMPOrderNoC", "First_MP_Order_No__c"));
		columns.add(new StringColumn(i++, "checkResultStatusC", "Check_Result_Status__c"));
		columns.add(new StringColumn(i++, "actualOrderInPartyC", "Actual_Order_in_Party__c"));
		
		columns.add(new StringColumn(i++, "currencyC", "Currency__c"));
		columns.add(new FakeColumn(i++, "quotePriceC", "Quote_Price__c"));
		columns.add(new FakeColumn(i++, "quotePriceUSDC", "Quote_Price_USD__c"));
		columns.add(new DateColumn(i++, "lifeCycleFromC", "Life_Cycle_from__c"));
		columns.add(new DateColumn(i++, "lifeCycleToC", "Life_Cycle_to__c"));
		columns.add(new DoubleColumn(i++, "SAMAvgQtyC", "SAM_Avg_Qty__c"));
		columns.add(new DoubleColumn(i++, "SOMAvgQtyC", "SOM_Avg_Qty__c"));
		columns.add(new StringColumn(i++, "createdById", "CreatedBy.FirstName,CreatedBy.LastName"));
		columns.add(new DateColumn(i++, "createdDate", "CreatedDate"));
		columns.add(new StringColumn(i++, "lastModifiedById", "LastModifiedById"));
		
		columns.add(new DateColumn(i++, "lastModifiedDate", "LastModifiedDate"));
		columns.add(new StringColumn(i++, "name", "Name"));
		columns.add(new StringColumn(i++, "productMigrationC", "Product_Migration__c"));
		columns.add(new StringColumn(i++, "competitor1IDC", "Competitor1_ID__c"));
		columns.add(new StringColumn(i++, "competitor2IDC", "Competitor2_ID__c"));
		columns.add(new StringColumn(i++, "competitor3IDC", "Competitor3_ID__c"));
		
		for (int index = 0; index < 10; index++) {
			columns.add(new FakeColumn(i++, "", ""));
		}
		
		this.tableName = "Opportunity.Product_Opportunity__r";
	}

	@Override
	public void postParse(ProductOpportunityC entity) {
	}

	protected String buildSfCondition(SfSqlConfig config) {
		return "";
	}

	@Override
	public void preFormat(ProductOpportunityC entity) {
		if (null != entity.getCreatedBy()) {
			entity.setCreatedById(this.formateAsName(entity.getCreatedBy()));
		}
		
	}

	@Override
	public void preFormat(Opportunity master, ProductOpportunityC entity) {
		entity.setId(master.getName());
	}
	
	
}
