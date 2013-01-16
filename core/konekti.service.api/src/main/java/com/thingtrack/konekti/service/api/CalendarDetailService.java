package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.CalendarDetail;

/**
 * @author Thingtrack S.L.
 *
 */
public interface CalendarDetailService {
	public List<CalendarDetail> getAll() throws Exception;
	public CalendarDetail get( Integer calendarDetailId ) throws Exception;
	public CalendarDetail save(CalendarDetail calendarDetail) throws Exception;
	public void delete(CalendarDetail calendarDetail) throws Exception;
}
