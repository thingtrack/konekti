package com.thingtrack.konekti.dao.sensor.impl.internal;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.sensor.api.SensorLocationDao;
import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.domain.sensor.Sensor;
import com.thingtrack.konekti.domain.sensor.SensorLocation;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class SensorLocationDaoImpl extends JpaDao<SensorLocation, Integer> implements SensorLocationDao {

	@Override
	public SensorLocation getByCode(String code) throws Exception {
		SensorLocation sensorLocation = (SensorLocation)getEntityManager()
		.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.code = :code")
		.setParameter("code", code).getSingleResult();

		return sensorLocation;
		
	}

	@Override
	public SensorLocation getByMac(String mac) throws Exception {
		SensorLocation sensorLocation = (SensorLocation)getEntityManager()
		.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.mac = :mac")
		.setParameter("mac", mac).getSingleResult();

		return sensorLocation;
		
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<SensorLocation> getAllActive() throws Exception {
		String queryString = "SELECT sl";
		queryString += " FROM SensorLocation sl";
		queryString += " JOIN sl.sensorStatus ss";		
		queryString += " WHERE ss.code = :code";
		
		Query query = (Query) getEntityManager()
		.createQuery(queryString)
		.setParameter("code", Sensor.STATUS.ACTIVE.name());
		
		return (List<SensorLocation>)query.getResultList();
	}
}
