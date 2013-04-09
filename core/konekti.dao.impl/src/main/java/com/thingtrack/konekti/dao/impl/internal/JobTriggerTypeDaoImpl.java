package com.thingtrack.konekti.dao.impl.internal;

import com.thingtrack.konekti.dao.api.JobTriggerTypeDao;
import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.domain.JobTriggerType;

public class JobTriggerTypeDaoImpl extends JpaDao<JobTriggerType, Integer> implements JobTriggerTypeDao {
	@Override
	public JobTriggerType getByCode(String code) throws Exception {
		JobTriggerType alarmTriggerType = (JobTriggerType)getEntityManager()
				.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.code = :code")
				.setParameter("code", code).getSingleResult();

		return alarmTriggerType;
	}
}
