package com.thingtrack.konekti.dao.impl.internal;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.dao.api.CalendarTypeDao;
import com.thingtrack.konekti.domain.CalendarType;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class CalendarTypeDaoImpl extends JpaDao<CalendarType, Integer> implements CalendarTypeDao {
	@Override
	public CalendarType getByCode(String code) throws Exception {
		CalendarType calendarType = (CalendarType)getEntityManager()
		.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.code = :code")
		.setParameter("code", code).getSingleResult();

		return calendarType;
		
	}

}
