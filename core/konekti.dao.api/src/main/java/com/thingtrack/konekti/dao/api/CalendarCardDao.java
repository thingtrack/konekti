package com.thingtrack.konekti.dao.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.CalendarCard;

/**
 * Calendar card Data Access Layer
 * <p>
 * @author Thingtrack S.L.
 *
 */
public interface CalendarCardDao extends Dao<CalendarCard, Integer> {
	
	/**
	 * Obtains an {@link CalendarCard} object found by its {@code month} 
	 * 
	 * @param month  the number of moth in the calendar
	 * @return {@code month} of the found {@link CalendarCard}, not null
	 * @throws Exception, if the number is between 0 to 11
	 */
	public CalendarCard getByMonth(Integer month) throws Exception;

}
