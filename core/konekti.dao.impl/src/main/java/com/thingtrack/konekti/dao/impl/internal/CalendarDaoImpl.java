package com.thingtrack.konekti.dao.impl.internal;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.dao.api.CalendarDao;
import com.thingtrack.konekti.domain.Calendar;

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

}
