package com.thingtrack.konekti.dao.impl.internal;

import com.thingtrack.konekti.dao.api.AlarmTriggerTypeDao;
import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.domain.AlarmTriggerType;

public class AlarmTriggerTypeDaoImpl extends JpaDao<AlarmTriggerType, Integer> implements AlarmTriggerTypeDao {
	@Override
	public AlarmTriggerType getByCode(String code) throws Exception {
		AlarmTriggerType alarmTriggerType = (AlarmTriggerType)getEntityManager()
				.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.code = :code")
				.setParameter("code", code).getSingleResult();

		return alarmTriggerType;
	}
}
