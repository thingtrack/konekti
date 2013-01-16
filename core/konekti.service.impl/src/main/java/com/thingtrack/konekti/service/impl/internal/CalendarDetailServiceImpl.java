package com.thingtrack.konekti.service.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.api.CalendarDetailDao;
import com.thingtrack.konekti.domain.CalendarDetail;
import com.thingtrack.konekti.service.api.CalendarDetailService;

/**
 * @author Thingtrack S.L.
 *
 */
public class CalendarDetailServiceImpl implements CalendarDetailService {
	@Autowired
	private CalendarDetailDao calendarDetailDao;

	@Override
	public List<CalendarDetail> getAll() throws Exception {
		return this.calendarDetailDao.getAll();
		
	}

	@Override
	public CalendarDetail get(Integer calendarDetailId) throws Exception {
		return this.calendarDetailDao.get(calendarDetailId);
		
	}

	@Override
	public CalendarDetail save(CalendarDetail calendarDetail) throws Exception {
		return this.calendarDetailDao.save(calendarDetail);
		
	}

	@Override
	public void delete(CalendarDetail calendarDetail) throws Exception {
		this.calendarDetailDao.delete(calendarDetail);
		
	}

}
