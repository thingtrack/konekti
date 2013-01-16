package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.CalendarCard;

/**
 * @author Thingtrack S.L.
 *
 */
public interface CalendarCardService {
	public List<CalendarCard> getAll() throws Exception;
	public CalendarCard get( Integer alarmTypeId ) throws Exception;
	public CalendarCard getByMonth( Integer month ) throws Exception;
	public CalendarCard save(CalendarCard calendarCard) throws Exception;
	public void delete(CalendarCard calendarCard) throws Exception;
}
