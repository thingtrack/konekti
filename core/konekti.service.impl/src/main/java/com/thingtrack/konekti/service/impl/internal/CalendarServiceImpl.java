package com.thingtrack.konekti.service.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.api.CalendarDao;
import com.thingtrack.konekti.domain.Calendar;
import com.thingtrack.konekti.service.api.CalendarService;

/**
 * @author Thingtrack S.L.
 *
 */
public class CalendarServiceImpl implements CalendarService {
	@Autowired
	private CalendarDao calendarDao;

	@Override
	public List<Calendar> getAll() throws Exception {
		return this.calendarDao.getAll();
		
	}

	@Override
	public Calendar get(Integer calendarId) throws Exception {
		return this.calendarDao.get(calendarId);
		
	}

	@Override
	public Calendar getByName(String name) throws Exception {
		return this.calendarDao.getByName(name);
		
	}

	@Override
	public Calendar save(Calendar calendar) throws Exception {
		return this.calendarDao.save(calendar);
		
	}

	@Override
	public void delete(Calendar calendar) throws Exception {
		this.calendarDao.delete(calendar);	
	}

}
