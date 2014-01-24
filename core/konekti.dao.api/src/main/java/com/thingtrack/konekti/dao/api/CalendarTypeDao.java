package com.thingtrack.konekti.dao.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.CalendarType;

/**
 * Calendar Type Data Access Layer 
 * <p>
 * @author Thingtrack S.L
 *
 */
public interface CalendarTypeDao extends Dao<CalendarType, Integer> {
	
	/**
	 * Obtains an {@link CalendarType} by its {@code code}
	 * 
	 * @param code  the unique code, not null
	 * @return the {@code CalendarType}
	 * @throws Exception if there is no {@code CalendarType} associated t the passed code or code is null
	 */
	public CalendarType getByCode(String code) throws Exception;

}
