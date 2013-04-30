package com.thingtrack.konekti.knowledge.service.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.domain.Calendar;
import com.thingtrack.konekti.domain.CalendarCard;
import com.thingtrack.konekti.domain.CalendarGroup;
import com.thingtrack.konekti.knowledge.service.api.CalendarKnowledgeService;
import com.thingtrack.konekti.service.api.CalendarCardService;
import com.thingtrack.konekti.service.api.CalendarGroupService;

public class CalendarKnowledgeServiceImpl implements CalendarKnowledgeService {
	@Autowired
	private CalendarCardService calendarCardService;
	
	@Autowired
	private CalendarGroupService calendarGroupService;
	
	@Override
	public void setCalendar(Calendar calendar, List<CalendarCard> calendarCards) throws Exception {
		// clear all calendar cards from calendar		
		for (CalendarCard calendarCard : calendar.getCalendarCards())
			calendarCardService.delete(calendarCard);
		
		// clear all calendar group from calendar
		for (CalendarGroup calendarGroup : calendar.getCalendarGroups())
			calendarGroupService.delete(calendarGroup);
		
		// add calendar cards from collection
		for (CalendarCard calendarCard : calendarCards)	{		
			CalendarCard savedCalendarCard = calendarCardService.save(calendarCard);		
			calendar.addCalendarCard(savedCalendarCard);
			
		}
		
	}

}
