package com.thingtrack.konekti.service.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.api.CalendarCardDao;
import com.thingtrack.konekti.domain.CalendarCard;
import com.thingtrack.konekti.service.api.CalendarCardService;

public class CalendarCardServiceImpl implements CalendarCardService {
	@Autowired
	private CalendarCardDao calendarCardDao;

	@Override
	public List<CalendarCard> getAll() throws Exception {
		return this.calendarCardDao.getAll();
		
	}

	@Override
	public CalendarCard get(Integer alarmTypeId) throws Exception {
		return this.calendarCardDao.get(alarmTypeId);
		
	}

	@Override
	public CalendarCard getByMonth(Integer month) throws Exception {
		return this.calendarCardDao.getByMonth(month);
		
	}

	@Override
	public CalendarCard save(CalendarCard calendarCard) throws Exception {
		return this.calendarCardDao.save(calendarCard);
		
	}

	@Override
	public void delete(CalendarCard calendarCard) throws Exception {
		this.calendarCardDao.delete(calendarCard);
		
	}
	
}
