package com.thingtrack.konekti.dao.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.Calendar;

/**
 * @author Thingtrack S.L
 *
 */
public interface CalendarDao extends Dao<Calendar, Integer> {
	public Calendar getByName(String name) throws Exception;
	public Calendar copy(Calendar calendar) throws Exception;

}
