package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.CalendarType;

/**
 * @author Thingtrack S.L.
 *
 */
public interface CalendarTypeService {
	public List<CalendarType> getAll() throws Exception;
	public CalendarType get( Integer calendarId ) throws Exception;
	public CalendarType getByCode( String code ) throws Exception;
	public CalendarType save(CalendarType calendarType) throws Exception;
	public void delete(CalendarType calendarType) throws Exception;
}
