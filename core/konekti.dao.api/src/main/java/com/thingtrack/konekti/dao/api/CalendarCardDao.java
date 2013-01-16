package com.thingtrack.konekti.dao.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.CalendarCard;

/**
 * @author Thingtrack S.L
 *
 */
public interface CalendarCardDao extends Dao<CalendarCard, Integer> {
	public CalendarCard getByMonth(Integer month) throws Exception;

}
