package com.thingtrack.konekti.service.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.api.CalendarGroupDao;
import com.thingtrack.konekti.domain.CalendarGroup;
import com.thingtrack.konekti.service.api.CalendarGroupService;

/**
 * @author Thingtrack S.L.
 *
 */
public class CalendarGroupServiceImpl implements CalendarGroupService {
	@Autowired
	private CalendarGroupDao calendarGroupDao;
	
	@Override
	public List<CalendarGroup> getAll() throws Exception {
		return this.calendarGroupDao.getAll();
	}

	@Override
	public CalendarGroup get(Integer calendarGroupId) throws Exception {
		return this.calendarGroupDao.get(calendarGroupId);
	}

	@Override
	public CalendarGroup save(CalendarGroup calendarGroup) throws Exception {
		return this.calendarGroupDao.save(calendarGroup);
	}

	@Override
	public void delete(CalendarGroup calendarGroup) throws Exception {
		this.calendarGroupDao.delete(calendarGroup);
		
	}

}
