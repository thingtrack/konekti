package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.CalendarGroup;

/**
 * @author Thingtrack S.L.
 *
 */
public interface CalendarGroupService {
	public List<CalendarGroup> getAll() throws Exception;
	public CalendarGroup get( Integer calendarGroupId ) throws Exception;
	public CalendarGroup save(CalendarGroup calendarGroup) throws Exception;
	public void delete(CalendarGroup calendarGroup) throws Exception;
}
