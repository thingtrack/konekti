package com.thingtrack.konekti.service.sensor.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.sensor.api.SensorTypeDao;
import com.thingtrack.konekti.domain.sensor.SensorType;
import com.thingtrack.konekti.service.sensor.api.SensorTypeService;

public class SensorTypeServiceImpl implements SensorTypeService {
	@Autowired
	private SensorTypeDao sensorTypeDao;
	
	@Override
	public List<SensorType> getAll() throws Exception {
		return this.sensorTypeDao.getAll();
		
	}

	@Override
	public SensorType get(Integer sensorTypeId) throws Exception {
		return this.sensorTypeDao.get(sensorTypeId);
		
	}

	@Override
	public SensorType getByCode(String code) throws Exception {
		return this.sensorTypeDao.getByCode(code);
		
	}

	@Override
	public SensorType save(SensorType sensorType) throws Exception {
		return this.sensorTypeDao.save(sensorType);
		
	}

	@Override
	public void delete(SensorType sensorType) throws Exception {
		this.sensorTypeDao.delete(sensorType);
		
	}

}
