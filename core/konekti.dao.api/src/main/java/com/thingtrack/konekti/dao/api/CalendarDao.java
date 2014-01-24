package com.thingtrack.konekti.dao.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.Calendar;

/**
 * Calendar Data Access Layer
 * <p>
 * @author Thingtrack S.L.
 *
 */
public interface CalendarDao extends Dao<Calendar, Integer> {
	
	/**
	 * Obtains an {@link Calendar} object found by its {@code name} 
	 * 
	 * @param name the unique name of the calendar, not null 
	 * @return {@link Calendar} found byt its name
	 * @throws Exception if the {@code name} not belongs to any stored {@code Calendar}
	 */
	public Calendar getByName(String name) throws Exception;
	
	/**
	 * Obtains a new {@link Calendar} cloned by the other passed as parameter
	 * 
	 * @param calendar the {@code Calendar} to be copied, not null
	 * @return A cloned {@code Calendar}
	 * @throws Exception if the passed {@code Calendar} is null
	 */
	public Calendar copy(Calendar calendar) throws Exception;

}
