package com.thingtrack.konekti.dao.sensor.impl.internal;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.sensor.api.SensorStatusDao;
import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.domain.sensor.SensorStatus;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class SensorStatusDaoImpl extends JpaDao<SensorStatus, Integer> implements SensorStatusDao {

	@Override
	public SensorStatus getByCode(String code) throws Exception {
		SensorStatus sensorStatus = (SensorStatus)getEntityManager()
		.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.code = :code")
		.setParameter("code", code).getSingleResult();

		return sensorStatus;
	}

}
