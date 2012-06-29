package com.sforce.parser;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.column.Column;
import com.sforce.column.StringColumn;
import com.sforce.soap.enterprise.sobject.ApplicationC;
/**
 * ApplicationC

Application_ID__c	Application_ID
Name	Name
Layer1_ID__c	Layer1_ID
Layer1_Desc__c	Layer1_Desc
Layer2_ID__c	Layer2_ID
Layer2_Desc__c	Layer2_Desc
Layer3_Desc__c	Layer3_Desc
Layer3_Rmk1__c	Layer3_Rmk1
Layer3_Rmk2__c	Layer3_Rmk2
Layer3_Rmk3__c	Layer3_Rmk3
Layer3_Rmk4__c	Layer3_Rmk4
Layer3_Rmk5__c	Layer3_Rmk5
Segment_Team_ID__c	Segment_Team_ID
Segment_Team_Desc__c	Segment_Team_Desc
Category_ID__c	Category_ID
Category_Desc__c	Category_Desc
Application_Team_ID__c	Application_Team_ID
Application_Team_Desc__c	Application_Team_Desc
Application_Group_ID__c	Application_Group_ID
Application_Group_Desc__c	Application_Group_Desc
 * @author elliot
 *
 */
public class Req09MasterParser extends BaseParser<ApplicationC>{
	private static final Logger logger = LoggerFactory.getLogger(Req09MasterParser.class);

	protected void initDefaultColumns() {
		this.columns = new ArrayList<Column<?>>();
		int i = 0;
		columns.add(new StringColumn(i++, "applicationIDC", "Application_ID__c"));
		columns.add(new StringColumn(i++, "name", "Name"));
		columns.add(new StringColumn(i++, "layer1IDC", "Layer1_ID"));
		columns.add(new StringColumn(i++, "layer1DescC", "Layer1_Desc"));
		columns.add(new StringColumn(i++, "layer2IDC", "Layer2_ID__c"));
		columns.add(new StringColumn(i++, "layer2DescC", "Layer2_Desc__c"));
		columns.add(new StringColumn(i++, "layer3DescC", "Layer3_Desc__c"));
		columns.add(new StringColumn(i++, "layer3Rmk1C", "Layer3_Rmk1__c"));
		columns.add(new StringColumn(i++, "layer3Rmk2C", "Layer3_Rmk2__c"));
		columns.add(new StringColumn(i++, "layer3Rmk3C", "Layer3_Rmk3__c"));
		columns.add(new StringColumn(i++, "layer3Rmk4C", "Layer3_Rmk4__c"));
		columns.add(new StringColumn(i++, "layer3Rmk5C", "Layer3_Rmk5__c"));
		columns.add(new StringColumn(i++, "segmentTeamIDC", "Segment_Team_ID__c"));
		columns.add(new StringColumn(i++, "segmentTeamDescC", "Segment_Team_Desc__c"));
		columns.add(new StringColumn(i++, "categoryIDC", "Category_ID__c"));
		columns.add(new StringColumn(i++, "categoryDescC", "Category_Desc__c"));
		columns.add(new StringColumn(i++, "applicationTeamIDC", "Application_Team_ID__c"));
		columns.add(new StringColumn(i++, "applicationTeamDescC", "Application_Team_Desc__c"));
		columns.add(new StringColumn(i++, "applicationGroupIDC", "Application_Group_ID__c"));
		columns.add(new StringColumn(i++, "applicationGroupDescC", "Application_Group_Desc__c"));
	}
	/*
	public ApplicationC parse(String[] source) {
		ApplicationC target = new ApplicationC();
		for (Column col : columns) {
			String s = source[col.getIndex()];
			try {
				if (null != col.getWriteMethod()) {
					col.getWriteMethod().invoke(target, col.parse(s));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return target;
	}
	*/
	public boolean accept(String[] source) {
		if (null != source && 20 == source.length) {
			return true;
		}
		return false;
	}
	
	
	@Override
	public Logger getLogger() {
		return logger;
	}

	@Override
	public void buildSyncKey(ApplicationC entity) {
		
	}
	@Override
	public void preFormat(ApplicationC entity) {
		// TODO Auto-generated method stub
		
	}
}
