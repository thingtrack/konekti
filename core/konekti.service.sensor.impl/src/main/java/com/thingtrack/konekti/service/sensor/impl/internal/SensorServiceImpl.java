package com.thingtrack.konekti.service.sensor.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.sensor.api.SensorDao;
import com.thingtrack.konekti.dao.sensor.api.SensorStatusDao;
import com.thingtrack.konekti.domain.sensor.Sensor;
import com.thingtrack.konekti.domain.sensor.SensorStatus;
import com.thingtrack.konekti.service.sensor.api.SensorService;

/**
 * @author Thingtrack S.L.
 *
 */
public class SensorServiceImpl implements SensorService {
	@Autowired
	private SensorDao sensorDao;
	
	@Autowired
	private SensorStatusDao sensorStatusDao;
	
	@Override
	public List<Sensor> getAll() throws Exception {
		return this.sensorDao.getAll();
		
	}

	@Override
	public Sensor get(Integer sensorId) throws Exception {
		return this.sensorDao.get(sensorId);
		
	}

	@Override
	public Sensor getByCode(String code) throws Exception {
		return this.sensorDao.getByCode(code);
	}

	@Override
	public Sensor getByMac(String mac) throws Exception {
		return this.sensorDao.getByMac(mac);
		
	}

	@Override
	public Sensor save(Sensor sensor) throws Exception {
		return this.sensorDao.save(sensor);
		
	}

	@Override
	public void delete(Sensor sensor) throws Exception {
		this.sensorDao.delete(sensor);
		
	}

	@Override
	public Sensor saveStatus(Sensor sensor, String code) throws Exception {
		SensorStatus sensorStatus = sensorStatusDao.getByCode(code);
		
		if (sensorStatus != null) {
			sensor.setSensorStatus(sensorStatus);
			
			return this.save(sensor);
		}
		
		return sensor;
	}

	@Override
	public List<Sensor> getAllActive() throws Exception {
		return this.sensorDao.getAllActive();
		
	}
}
