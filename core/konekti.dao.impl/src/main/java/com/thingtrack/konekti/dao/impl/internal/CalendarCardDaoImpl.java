package com.thingtrack.konekti.dao.impl.internal;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.dao.api.CalendarCardDao;
import com.thingtrack.konekti.domain.CalendarCard;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class CalendarCardDaoImpl extends JpaDao<CalendarCard, Integer> implements CalendarCardDao {
	@Override
	public CalendarCard getByMonth(Integer month) throws Exception {
		CalendarCard calendarCard = (CalendarCard)getEntityManager()
		.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.month = :month")
		.setParameter("month", month).getSingleResult();

		return calendarCard;
		
	}
	
}
