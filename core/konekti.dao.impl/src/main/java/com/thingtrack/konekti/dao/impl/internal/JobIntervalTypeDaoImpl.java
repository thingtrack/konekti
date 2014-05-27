package com.thingtrack.konekti.dao.impl.internal;

import com.thingtrack.konekti.dao.api.JobIntervalTypeDao;
import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.domain.JobIntervalType;

public class JobIntervalTypeDaoImpl extends JpaDao<JobIntervalType, Integer> implements JobIntervalTypeDao {
	@Override
	public JobIntervalType getByCode(String code) throws Exception {
		JobIntervalType alarmIntervalType = (JobIntervalType)getEntityManager()
				.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.code = :code")
				.setParameter("code", code).getSingleResult();

		return alarmIntervalType;
	}
}
