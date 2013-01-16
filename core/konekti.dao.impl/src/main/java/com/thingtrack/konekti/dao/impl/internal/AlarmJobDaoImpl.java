package com.thingtrack.konekti.dao.impl.internal;

import com.thingtrack.konekti.dao.api.AlarmJobDao;
import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.domain.AlarmJob;

public class AlarmJobDaoImpl extends JpaDao<AlarmJob, Integer> implements AlarmJobDao {

	@Override
	public AlarmJob getByGroupName(String group, String name) throws Exception {
		AlarmJob alarmJob = (AlarmJob)getEntityManager()
				.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.alarmGroup = :group AND p.alarmName = :name")
				.setParameter("group", group)
				.setParameter("name", name).getSingleResult();

		return alarmJob;
	}

}
