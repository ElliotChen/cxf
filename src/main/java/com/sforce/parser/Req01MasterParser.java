package com.sforce.parser;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.column.Column;
import com.sforce.column.DateColumn;
import com.sforce.column.DoubleColumn;
import com.sforce.column.StringColumn;
import com.sforce.soap.enterprise.sobject.ExchangeRateC;
import com.sforce.util.DateUtils;

public class Req01MasterParser extends BaseParser<ExchangeRateC> {
	private static final Logger logger = LoggerFactory.getLogger(Req01MasterParser.class);
	
	@Override
	public boolean accept(String[] source) {
		if (3 != source.length) {
			return false;
		}
		return true;
	}

	@Override
	public Logger getLogger() {
		return logger;
	}

	@Override
	protected void initDefaultColumns() {
		this.columns = new ArrayList<Column>();
		int i = 0;
		columns.add(new DateColumn(i++, "dateC", "Date__c"));
		columns.add(new StringColumn(i++, "currencyC", "Currency__c"));
		columns.add(new DoubleColumn(i++, "exchangeRateC", "Exchange_Rate__c"));
	}

	@Override
	public void buildSyncKey(ExchangeRateC entity) {
		if (null != entity.getDateC() && StringUtils.isNotEmpty(entity.getCurrencyC())) {
			entity.setKeySyncC(DateUtils.formatDate(entity.getDateC())+entity.getCurrencyC());
		} else {
			logger.error("Key column date[{}], currency[{}] can't be empty", entity.getDateC(), entity.getCurrencyC());
		}
	}

}
