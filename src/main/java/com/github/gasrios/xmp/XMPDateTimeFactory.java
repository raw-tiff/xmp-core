// =================================================================================================
// ADOBE SYSTEMS INCORPORATED
// Copyright 2006 Adobe Systems Incorporated
// All Rights Reserved
//
// NOTICE:  Adobe permits you to use, modify, and distribute this file in accordance with the terms
// of the Adobe license agreement accompanying it.
// =================================================================================================

package com.github.gasrios.xmp;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import com.github.gasrios.xmp.impl.XMPDateTimeImpl;

public final class XMPDateTimeFactory {

	private static final TimeZone UTC = TimeZone.getTimeZone("UTC");

	private XMPDateTimeFactory() {}

	public static XMPDateTime createFromCalendar(Calendar calendar) {
		return new XMPDateTimeImpl(calendar);
	}

	public static XMPDateTime create() {
		return new XMPDateTimeImpl();
	}

	public static XMPDateTime create(int year, int month, int day) {
		XMPDateTime dt = new XMPDateTimeImpl();
		dt.setYear(year);
		dt.setMonth(month);
		dt.setDay(day);
		return dt;
	}

	public static XMPDateTime create(int year, int month, int day, int hour, int minute, int second, int nanoSecond) {
		XMPDateTime dt = new XMPDateTimeImpl();
		dt.setYear(year);
		dt.setMonth(month);
		dt.setDay(day);
		dt.setHour(hour);
		dt.setMinute(minute);
		dt.setSecond(second);
		dt.setNanoSecond(nanoSecond);
		return dt;
	}

	public static XMPDateTime createFromISO8601(String strValue) throws XMPException {
		return new XMPDateTimeImpl(strValue);
	}

	public static XMPDateTime getCurrentDateTime() {
		return new XMPDateTimeImpl(new GregorianCalendar());
	}

	public static XMPDateTime setLocalTimeZone(XMPDateTime dateTime) {
		Calendar cal = dateTime.getCalendar();
		cal.setTimeZone(TimeZone.getDefault());
		return new XMPDateTimeImpl(cal);
	}

	public static XMPDateTime convertToUTCTime(XMPDateTime dateTime) {
		long timeInMillis = dateTime.getCalendar().getTimeInMillis();
		GregorianCalendar cal = new GregorianCalendar(UTC);
		cal.setGregorianChange(new Date(Long.MIN_VALUE));
		cal.setTimeInMillis(timeInMillis);
		return new XMPDateTimeImpl(cal);
	}

	public static XMPDateTime convertToLocalTime(XMPDateTime dateTime) {
		long timeInMillis = dateTime.getCalendar().getTimeInMillis();
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTimeInMillis(timeInMillis);
		return new XMPDateTimeImpl(cal);
	}

}