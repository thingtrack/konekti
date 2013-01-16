package com.thingtrack.konekti.service.sensor.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.sensor.api.SensorLocationDao;
import com.thingtrack.konekti.dao.sensor.api.SensorStatusDao;
import com.thingtrack.konekti.domain.sensor.SensorLocation;
import com.thingtrack.konekti.domain.sensor.SensorStatus;
import com.thingtrack.konekti.service.sensor.api.SensorLocationService;

public class SensorLocationServiceImpl implements SensorLocationService {
	@Autowired
	private SensorLocationDao sensorLocationDao;
	
	@Autowired
	private SensorStatusDao sensorStatusDao;
	
	@Override
	public List<SensorLocation> getAll() throws Exception {
		return this.sensorLocationDao.getAll();
	}

	@Override
	public SensorLocation get(Integer sensorLocationId) throws Exception {
		return this.sensorLocationDao.get(sensorLocationId);
		
	}

	@Override
	public SensorLocation getByCode(String code) throws Exception {
		return this.sensorLocationDao.getByCode(code);
		
	}

	@Override
	public SensorLocation getByMac(String mac) throws Exception {
		return this.sensorLocationDao.getByMac(mac);
		
	}

	@Override
	public SensorLocation save(SensorLocation sensorLocation) throws Exception {
		return this.sensorLocationDao.save(sensorLocation);
		
	}

	@Override
	public void delete(SensorLocation sensorLocation) throws Exception {
		this.sensorLocationDao.delete(sensorLocation);
		
	}

	@Override
	public SensorLocation saveStatus(SensorLocation sensorLocation, String code) throws Exception {
		SensorStatus sensorStatus = sensorStatusDao.getByCode(code);
		
		if (sensorStatus != null) {
			sensorLocation.setSensorStatus(sensorStatus);
			
			return this.save(sensorLocation);
		}
		
		return sensorLocation;
	}

	@Override
	public List<SensorLocation> getAllActive() throws Exception {
		return this.sensorLocationDao.getAllActive();
	}
}
