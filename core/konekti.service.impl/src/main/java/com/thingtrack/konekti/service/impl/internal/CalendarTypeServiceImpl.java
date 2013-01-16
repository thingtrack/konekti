package com.thingtrack.konekti.service.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.api.CalendarTypeDao;
import com.thingtrack.konekti.domain.CalendarType;
import com.thingtrack.konekti.service.api.CalendarTypeService;

/**
 * @author Thingtrack S.L.
 *
 */
public class CalendarTypeServiceImpl  implements CalendarTypeService {
	@Autowired
	private CalendarTypeDao calendarTypeDao;

	@Override
	public List<CalendarType> getAll() throws Exception {
		return this.calendarTypeDao.getAll();
		
	}

	@Override
	public CalendarType get(Integer calendarId) throws Exception {
		return this.calendarTypeDao.get(calendarId);
		
	}

	@Override
	public CalendarType getByCode(String code) throws Exception {
		return this.calendarTypeDao.getByCode(code);
		
	}

	@Override
	public CalendarType save(CalendarType calendarType) throws Exception {
		return this.calendarTypeDao.save(calendarType);
		
	}

	@Override
	public void delete(CalendarType calendarType) throws Exception {
		this.calendarTypeDao.delete(calendarType);
		
	}

}
