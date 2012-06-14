package com.sforce.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DateUtils {
	private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);
	
	private static ThreadLocal<SimpleDateFormat> defaultDateFormat = new ThreadLocal<SimpleDateFormat>();
	
	private static ThreadLocal<SimpleDateFormat> defaultDateTimeFormat = new ThreadLocal<SimpleDateFormat>();
	
	private static ThreadLocal<SimpleDateFormat> defaultTimeFormat = new ThreadLocal<SimpleDateFormat>();
	
	private static ThreadLocal<SimpleDateFormat> defaultMessageIdFormat = new ThreadLocal<SimpleDateFormat>();
	
	private static ThreadLocal<SimpleDateFormat> defaultPathFormat = new ThreadLocal<SimpleDateFormat>();
	
	public static final SimpleDateFormat getDefaultDateFormat() {
		if (null == defaultDateFormat.get()) {
			defaultDateFormat.set(new SimpleDateFormat("yyyyMMdd"));
		}
		
		return defaultDateFormat.get();
	}
	
	public static final SimpleDateFormat getDefaultTimeFormat() {
		if (null == defaultTimeFormat.get()) {
			defaultTimeFormat.set(new SimpleDateFormat("HHmmss"));
		}
		
		return defaultTimeFormat.get();
	}
	
	public static final SimpleDateFormat getDefaultDateTimeFormat() {
		if (null == defaultDateTimeFormat.get()) {
			defaultDateTimeFormat.set(new SimpleDateFormat("yyyyMMdd HHmmss"));
		}
		
		return defaultDateTimeFormat.get();
	}
	
	public static final SimpleDateFormat getDefaultMessageIdFormat() {
		if (null == defaultMessageIdFormat.get()) {
			defaultMessageIdFormat.set(new SimpleDateFormat("yyyyMMdd_HHmmss"));
		}
		
		return defaultMessageIdFormat.get();
	}
	
	public static final SimpleDateFormat getDefaultPathFormat() {
		if (null == defaultPathFormat.get()) {
			defaultPathFormat.set(new SimpleDateFormat("yyyy/MM/dd"));
		}
		
		return defaultPathFormat.get();
	}
	
	public static final Date pareseDate(String date) {
		Date result = null;
		try {
			result = getDefaultDateFormat().parse(date);
		} catch (ParseException e) {
			logger.error("Can't parse {} to Date", date);
		}
		
		return result;
	}
	
	public static final Date pareseDateTime(String date) {
		Date result = null;
		try {
			result = getDefaultDateFormat().parse(date);
		} catch (ParseException e) {
			logger.error("Can't parse {} to Date", date);
		}
		
		return result;
	}
	
	public static final String formatDate(Date date) {
		return getDefaultDateFormat().format(date);
	}
	
	public static final String formatDateTime(Date date) {
		return getDefaultDateTimeFormat().format(date);
	}
	
	public static final String formatMessageId(Date date) {
		return getDefaultMessageIdFormat().format(date);
	}
	
	public static final String formatPath(Date date) {
		return getDefaultPathFormat().format(date);
	}
	
	public static final Date begin(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTime();
	}
	
	public static final Date end(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTime();
	}
}
