package com.thingtrack.konekti.knowledge.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.Calendar;
import com.thingtrack.konekti.domain.CalendarCard;

public interface CalendarKnowledgeService {
	public void setCalendar(Calendar Calendar, List<CalendarCard> calendarCards) throws Exception;
}
