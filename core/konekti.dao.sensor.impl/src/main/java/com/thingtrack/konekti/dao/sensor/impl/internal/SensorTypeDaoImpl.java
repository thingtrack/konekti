package com.thingtrack.konekti.dao.sensor.impl.internal;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.sensor.api.SensorTypeDao;
import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.domain.sensor.SensorType;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class SensorTypeDaoImpl extends JpaDao<SensorType, Integer> implements SensorTypeDao {

	@Override
	public SensorType getByCode(String code) throws Exception {
		SensorType sensorType = (SensorType)getEntityManager()
		.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.code = :code")
		.setParameter("code", code).getSingleResult();

		return sensorType;
	}

}
