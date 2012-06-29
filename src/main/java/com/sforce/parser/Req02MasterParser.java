package com.sforce.parser;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.column.Column;
import com.sforce.column.DateColumn;
import com.sforce.column.DoubleColumn;
import com.sforce.column.StringColumn;
import com.sforce.soap.enterprise.sobject.EPNMasterC;
import com.sforce.soap.enterprise.sobject.ExchangeRateC;
/**
 * groupLineC	Group_Line__c
keySyncC	Key_sync__c
productTypeC	Product_Type__c
densityC	Density__c
voltageC	Voltage__c
pkgTypeC	Pkg_Type__c
PKGNameC	PKG_Name__c
pinCountC	Pin_Count__c
gradeC	Grade__c
carGradeFlagC	Car_Grade_Flag__c
bodySizeC	Body_Size__c
brandC	Brand__c
expiredDateC	Expired_Date__c
markForDeleteC	Mark_for_Delete__c
EOLFlagC	EOL_Flag__c
EOLIssueDateC	EOL_Issue_Date__c
LODateC	LO_Date__c
LSDateC	LS_Date__c
 * @author elliot
 *
 */
public class Req02MasterParser extends BaseParser<EPNMasterC> {
	private static final Logger logger = LoggerFactory.getLogger(Req02MasterParser.class);
	
	@Override
	public boolean accept(String[] source) {
		return 18 == source.length;
	}

	@Override
	public Logger getLogger() {
		return logger;
	}

	@Override
	protected void initDefaultColumns() {
		this.columns = new ArrayList<Column<?>>();
		int i = 0;
		columns.add(new StringColumn(i++, "groupLineC", "Group_Line__c"));
		columns.add(new StringColumn(i++, "name", "Name"));
//		columns.add(new StringColumn(i++, "keySyncC", "Key_sync__c"));
		columns.add(new StringColumn(i++, "productTypeC", "Product_Type__c"));
		columns.add(new StringColumn(i++, "densityC", "Density__c"));
		columns.add(new StringColumn(i++, "voltageC", "Voltage__c"));
		columns.add(new StringColumn(i++, "PKGTypeC", "Pkg_Type__c"));
		columns.add(new StringColumn(i++, "PKGNameC", "PKG_Name__c"));
		columns.add(new StringColumn(i++, "pinCountC", "Pin_Count__c"));
		columns.add(new StringColumn(i++, "gradeC", "Grade__c"));
		columns.add(new StringColumn(i++, "carGradeFlagC", "Car_Grade_Flag__c"));
		columns.add(new StringColumn(i++, "brandC", "Brand__c"));
		columns.add(new StringColumn(i++, "bodySizeC", "Body_Size__c"));
		columns.add(new DateColumn(i++, "expiredDateC", "Expired_Date__c"));
		columns.add(new StringColumn(i++, "markForDeleteC", "Mark_for_Delete__c"));
		columns.add(new StringColumn(i++, "EOLFlagC", "EOL_Flag__c"));
		columns.add(new DateColumn(i++, "EOLIssueDateC", "EOL_Issue_Date__c"));
		columns.add(new DateColumn(i++, "LODateC", "LO_Date__c"));
		columns.add(new DateColumn(i++, "LSDateC", "LS_Date__c"));
	}

	@Override
	public void buildSyncKey(EPNMasterC entity) {
		entity.setKeySyncC(entity.getName());
	}

	@Override
	public void preFormat(EPNMasterC entity) {
		// TODO Auto-generated method stub
		
	}

}
