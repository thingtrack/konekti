package com.thingtrack.konekti.dao.sensor.impl.internal;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.sensor.api.SensorDao;
import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.domain.sensor.Sensor;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class SensorDaoImpl extends JpaDao<Sensor, Integer> implements SensorDao {

	@Override
	public Sensor getByCode(String code) throws Exception {
		Sensor sensor = (Sensor)getEntityManager()
		.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.code = :code")
		.setParameter("code", code).getSingleResult();

		return sensor;

	}

	@Override
	public Sensor getByMac(String mac) throws Exception {
		Sensor sensor = (Sensor)getEntityManager()
		.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.mac = :mac")
		.setParameter("mac", mac).getSingleResult();

		return sensor;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Sensor> getAllActive() throws Exception {
		String queryString = "SELECT s";
		queryString += " FROM Sensor s";
		queryString += " JOIN s.sensorStatus ss";		
		queryString += " WHERE ss.code = :code";
		
		Query query = (Query) getEntityManager()
		.createQuery(queryString)
		.setParameter("code", Sensor.STATUS.ACTIVE.name());
		
		return  (List<Sensor>)query.getResultList();
	}
	
}
