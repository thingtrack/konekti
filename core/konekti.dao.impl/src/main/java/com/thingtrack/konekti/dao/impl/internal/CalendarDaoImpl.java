package com.thingtrack.konekti.dao.impl.internal;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.dao.api.CalendarDao;
import com.thingtrack.konekti.domain.Calendar;
import com.thingtrack.konekti.domain.CalendarCard;
import com.thingtrack.konekti.domain.CalendarDetail;
import com.thingtrack.konekti.domain.CalendarGroup;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class CalendarDaoImpl extends JpaDao<Calendar, Integer> implements CalendarDao {
	@Override
	public Calendar getByName(String name) throws Exception {
		Calendar calendar = (Calendar)getEntityManager()
		.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.name = :name")
		.setParameter("name", name).getSingleResult();

		return calendar;
		
	}

	@Override
	public Calendar copy(Calendar calendar) throws Exception {
		
		getEntityManager().detach(calendar);
		
		//Remove ids
		for(CalendarCard calendarCard : calendar.getCalendarCards()){
			for(CalendarDetail calendarDetail : calendarCard.getCalendarDetails())
				calendarDetail.setCalendarDetailId(null);
			calendarCard.setCalendarCardId(null);
		}
		
		for(CalendarGroup calendarGroup : calendar.getCalendarGroups())
			calendarGroup.setCalendarGroupId(null);
		
		calendar.setCalendarId(null);

		return calendar;
	}

}
