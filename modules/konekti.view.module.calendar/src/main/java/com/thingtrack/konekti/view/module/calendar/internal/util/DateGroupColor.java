package com.thingtrack.konekti.view.module.calendar.internal.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.thingtrack.konekti.domain.CalendarGroup;

public class DateGroupColor {
	private String selector;
	private CalendarGroup calendarGroup;
	private List<Date> dates = new ArrayList<Date>();			
	
	public DateGroupColor(String selector, CalendarGroup calendarGroup) {
		this.selector = selector;
		this.calendarGroup = calendarGroup;
		
	}
	
	/**
	 * @return the dates
	 */
	public List<Date> getDates() {
		return dates;
	}
	
	/**
	 * @param dates the dates to set
	 */
	public void setDates(List<Date> dates) {
		this.dates = dates;
	}

	/**
	 * @return the selector
	 */
	public String getSelector() {
		return selector;
	}

	/**
	 * @param selector the selector to set
	 */
	public void setSelector(String selector) {
		this.selector = selector;
	}

	/**
	 * @return the calendarGroup
	 */
	public CalendarGroup getCalendarGroup() {
		return calendarGroup;
	}

	/**
	 * @param calendarGroup the calendarGroup to set
	 */
	public void setCalendarGroup(CalendarGroup calendarGroup) {
		this.calendarGroup = calendarGroup;
	}

}
