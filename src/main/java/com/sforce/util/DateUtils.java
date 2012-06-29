package com.sforce.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DateUtils {
	private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);
	
	private static ThreadLocal<SimpleDateFormat> defaultDateFormat = new ThreadLocal<SimpleDateFormat>();
	
	private static ThreadLocal<SimpleDateFormat> defaultMonthFormat = new ThreadLocal<SimpleDateFormat>();
	
	private static ThreadLocal<SimpleDateFormat> defaultDateTimeFormat = new ThreadLocal<SimpleDateFormat>();
	
	private static ThreadLocal<SimpleDateFormat> defaultTimeFormat = new ThreadLocal<SimpleDateFormat>();
	
	private static ThreadLocal<SimpleDateFormat> defaultMessageIdFormat = new ThreadLocal<SimpleDateFormat>();
	
	private static ThreadLocal<SimpleDateFormat> defaultPathFormat = new ThreadLocal<SimpleDateFormat>();
	
	private static ThreadLocal<SimpleDateFormat> defaultSfDateTimeFormat = new ThreadLocal<SimpleDateFormat>();
	public static final SimpleDateFormat getDefaultDateFormat() {
		if (null == defaultDateFormat.get()) {
			defaultDateFormat.set(new SimpleDateFormat("yyyyMMdd", Locale.TAIWAN));
		}
		
		return defaultDateFormat.get();
	}
	
	public static final SimpleDateFormat getDefaultMonthFormat() {
		if (null == defaultMonthFormat.get()) {
			defaultMonthFormat.set(new SimpleDateFormat("yyyy/MM", Locale.TAIWAN));
		}
		
		return defaultMonthFormat.get();
	}
	
	public static final SimpleDateFormat getDefaultTimeFormat() {
		if (null == defaultTimeFormat.get()) {
			defaultTimeFormat.set(new SimpleDateFormat("HHmmss", Locale.TAIWAN));
		}
		
		return defaultTimeFormat.get();
	}
	
	public static final SimpleDateFormat getDefaultDateTimeFormat() {
		if (null == defaultDateTimeFormat.get()) {
			defaultDateTimeFormat.set(new SimpleDateFormat("yyyyMMdd HHmmss", Locale.TAIWAN));
		}
		
		return defaultDateTimeFormat.get();
	}
	
	public static final SimpleDateFormat getDefaultSfDateTimeFormat() {
		if (null == defaultSfDateTimeFormat.get()) {
			defaultSfDateTimeFormat.set(new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ssZ"));
		}
		
		return defaultSfDateTimeFormat.get();
	}
	
	public static final SimpleDateFormat getDefaultMessageIdFormat() {
		if (null == defaultMessageIdFormat.get()) {
			defaultMessageIdFormat.set(new SimpleDateFormat("yyyyMMddHHmmss"));
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
	
	public static final Date pareseMonth(String date) {
		Date result = null;
		try {
			result = getDefaultMonthFormat().parse(date);
		} catch (ParseException e) {
			logger.error("Can't parse {} to Month", date);
		}
		
		return result;
	}
	
	public static final Date pareseDateTime(String date) {
		Date result = null;
		try {
			result = getDefaultDateTimeFormat().parse(date);
		} catch (ParseException e) {
			logger.error("Can't parse {} to Date Time", date);
		}
		
		return result;
	}
	
	public static final Date pareseTime(String date) {
		Date result = null;
		try {
			result = getDefaultTimeFormat().parse(date);
		} catch (ParseException e) {
			logger.error("Can't parse {} to Time", date);
		}
		
		return result;
	}
	
	public static final String formatDate(Date date) {
		return getDefaultDateFormat().format(date);
	}
	
	public static final String formatDateTime(Date date) {
		return getDefaultDateTimeFormat().format(date);
	}
	
	public static final String formatTime(Date date) {
		return getDefaultTimeFormat().format(date);
	}
	
	public static final String formatMonth(Date date) {
		return getDefaultMonthFormat().format(date);
	}
	
	public static final String formatSfDateTime(Date date) {
		return getDefaultSfDateTimeFormat().format(date);
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
