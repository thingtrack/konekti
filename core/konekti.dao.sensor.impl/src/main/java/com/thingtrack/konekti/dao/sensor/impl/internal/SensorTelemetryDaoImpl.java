package com.thingtrack.konekti.dao.sensor.impl.internal;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.sensor.api.SensorTelemetryDao;
import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.domain.sensor.Sensor;
import com.thingtrack.konekti.domain.sensor.SensorTelemetry;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class SensorTelemetryDaoImpl extends JpaDao<SensorTelemetry, Integer> implements SensorTelemetryDao {

	@Override
	public SensorTelemetry getByCode(String code) throws Exception {
		SensorTelemetry sensorTelemetry = (SensorTelemetry)getEntityManager()
		.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.code = :code")
		.setParameter("code", code).getSingleResult();

		return sensorTelemetry;
		
	}

	@Override
	public SensorTelemetry getByMac(String mac) throws Exception {
		SensorTelemetry sensorTelemetry = (SensorTelemetry)getEntityManager()
		.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.mac = :mac")
		.setParameter("mac", mac).getSingleResult();

		return sensorTelemetry;
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SensorTelemetry> getAllActive() throws Exception {
		String queryString = "SELECT st";
		queryString += " FROM SensorTelemetry st";
		queryString += " JOIN st.sensorStatus ss";		
		queryString += " WHERE ss.code = :code";
		
		Query query = (Query) getEntityManager()
		.createQuery(queryString)
		.setParameter("code", Sensor.STATUS.ACTIVE.name());
		
		return (List<SensorTelemetry>)query.getResultList();
	}
}
