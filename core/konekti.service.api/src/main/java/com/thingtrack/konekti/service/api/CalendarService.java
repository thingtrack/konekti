package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.Calendar;

/**
 * @author Thingtrack S.L.
 *
 */
public interface CalendarService {
	public List<Calendar> getAll() throws Exception;
	public Calendar get( Integer calendarId ) throws Exception;
	public Calendar getByName( String name ) throws Exception;
	public Calendar save(Calendar calendar) throws Exception;
	public void delete(Calendar calendar) throws Exception;
}
