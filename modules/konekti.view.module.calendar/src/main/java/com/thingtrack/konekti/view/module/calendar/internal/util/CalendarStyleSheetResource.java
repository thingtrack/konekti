package com.thingtrack.konekti.view.module.calendar.internal.util;

import java.util.Enumeration;
import java.util.Hashtable;

public class CalendarStyleSheetResource {

	public static final String CALENDAR_BASE_SELECTOR = "date-group-style-";
	
	private Hashtable<String, String> cssRuleTable = new Hashtable<String, String>();

	public String addCssRule(String color) {		
		String selector = CALENDAR_BASE_SELECTOR + Math.round(Math.random() * 100);
		String cssRule =  "." + selector + " { background-color: " + color + "; }\n";
		
		cssRuleTable.put(selector, cssRule);
		
		return selector;
	}
	
	public boolean updateCssRule(String selector, String color) {
		cssRuleTable.remove(selector);		
		String cssRule =  "." + selector + " { background-color: " + color + "; }\n";		
		cssRuleTable.put(selector, cssRule);
		
		return true;
	}
	
	public boolean removeCssRule(String selector) {		
		return cssRuleTable.remove(selector) != null;
		
	}
	
	public String getCss() {
		StringBuilder cssStyleSheetStringBuilder = new StringBuilder();
		
		Enumeration<String> selectors = cssRuleTable.keys();
		
		while(selectors.hasMoreElements()) {
			String cssRule = (String) cssRuleTable.get(selectors.nextElement());
			cssStyleSheetStringBuilder.append(cssRule);
		}
		
		return cssStyleSheetStringBuilder.toString();
	}
}
