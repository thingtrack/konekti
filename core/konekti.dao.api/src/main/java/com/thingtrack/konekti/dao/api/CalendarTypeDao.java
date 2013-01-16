package com.thingtrack.konekti.dao.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.CalendarType;

/**
 * @author Thingtrack S.L
 *
 */
public interface CalendarTypeDao extends Dao<CalendarType, Integer> {
	public CalendarType getByCode(String code) throws Exception;

}
