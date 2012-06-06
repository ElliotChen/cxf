package com.sforce.parser;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.sforce.soap.enterprise.SforceServiceTest;
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
public class Req09Parser {
	private static final Logger logger = LoggerFactory.getLogger(Req09Parser.class);
	List<Column> columns = new ArrayList<Column>();

	public void init() {
		int i = 0;
		columns.add(new StringColumn(i++, "applicationIDC", "Application_ID__c"));
		columns.add(new StringColumn(i++, "name", "Name"));
		columns.add(new StringColumn(i++, "layer1IDC", "Layer1_ID"));
		columns.add(new StringColumn(i++, "layer1DescC", "Layer1_Desc"));
		/*
		columns.add(new StringColumn(i++, "", ""));
		columns.add(new StringColumn(i++, "", ""));
		columns.add(new StringColumn(i++, "", ""));
		columns.add(new StringColumn(i++, "", ""));
		columns.add(new StringColumn(i++, "", ""));
		columns.add(new StringColumn(i++, "", ""));
		columns.add(new StringColumn(i++, "", ""));
		columns.add(new StringColumn(i++, "", ""));
		columns.add(new StringColumn(i++, "", ""));
		columns.add(new StringColumn(i++, "", ""));
		columns.add(new StringColumn(i++, "", ""));
		columns.add(new StringColumn(i++, "", ""));
		columns.add(new StringColumn(i++, "", ""));
		columns.add(new StringColumn(i++, "", ""));
		columns.add(new StringColumn(i++, "", ""));
		columns.add(new StringColumn(i++, "", ""));*/
		PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(ApplicationC.class);

		for (Column col : columns) {
			for (PropertyDescriptor pd : propertyDescriptors) {
				if (pd.getName().equals(col.getName())) {
					col.setReadMethod(pd.getReadMethod());
					col.setWriteMethod(pd.getWriteMethod());
					break;
				}
			}
			if (null == col.getReadMethod()) {
				logger.error("Can't find ReadMethod for col[{}]", col.getName());
			}
			if (null == col.getWriteMethod()) {
				logger.error("Can't find WriteMethod for col[{}]", col.getName());
			}
		}
	}

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
}
