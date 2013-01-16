package com.thingtrack.konekti.dao.sensor.impl.internal;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.sensor.api.SensorMessageDao;
import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.domain.sensor.Sensor;
import com.thingtrack.konekti.domain.sensor.SensorMessage;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class SensorMessageDaoImpl extends JpaDao<SensorMessage, Integer> implements SensorMessageDao {

	@Override
	public SensorMessage getByCode(String code) throws Exception {
		SensorMessage sensorMessage = (SensorMessage)getEntityManager()
		.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.code = :code")
		.setParameter("code", code).getSingleResult();

		return sensorMessage;
		
	}

	@Override
	public SensorMessage getByMac(String mac) throws Exception {
		SensorMessage sensorMessage = (SensorMessage)getEntityManager()
		.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.mac = :mac")
		.setParameter("mac", mac).getSingleResult();

		return sensorMessage;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SensorMessage> getAllActive() throws Exception {
		String queryString = "SELECT sm";
		queryString += " FROM SensorMessage sm";
		queryString += " JOIN sm.sensorStatus ss";		
		queryString += " WHERE ss.code = :code";
		
		Query query = (Query) getEntityManager()
		.createQuery(queryString)
		.setParameter("code", Sensor.STATUS.ACTIVE.name());
		
		return (List<SensorMessage>)query.getResultList();
	}

}
