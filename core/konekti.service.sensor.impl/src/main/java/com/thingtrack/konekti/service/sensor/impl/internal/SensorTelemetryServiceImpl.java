package com.thingtrack.konekti.service.sensor.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.sensor.api.SensorStatusDao;
import com.thingtrack.konekti.dao.sensor.api.SensorTelemetryDao;
import com.thingtrack.konekti.domain.sensor.SensorStatus;
import com.thingtrack.konekti.domain.sensor.SensorTelemetry;
import com.thingtrack.konekti.service.sensor.api.SensorTelemetryService;

public class SensorTelemetryServiceImpl implements SensorTelemetryService {
	@Autowired
	private SensorTelemetryDao sensorTelemetryDao;

	@Autowired
	private SensorStatusDao sensorStatusDao;
	
	@Override
	public List<SensorTelemetry> getAll() throws Exception {
		return this.sensorTelemetryDao.getAll();
		
	}

	@Override
	public SensorTelemetry get(Integer sensorTelemetryId) throws Exception {
		return this.sensorTelemetryDao.get(sensorTelemetryId);
		
	}

	@Override
	public SensorTelemetry getByCode(String code) throws Exception {
		return this.sensorTelemetryDao.getByCode(code);
		
	}

	@Override
	public SensorTelemetry getByMac(String mac) throws Exception {
		return this.sensorTelemetryDao.getByMac(mac);
		
	}

	@Override
	public SensorTelemetry save(SensorTelemetry sensorTelemetry)
			throws Exception {
		return this.sensorTelemetryDao.save(sensorTelemetry);
		
	}

	@Override
	public void delete(SensorTelemetry sensorTelemetry) throws Exception {
		this.sensorTelemetryDao.delete(sensorTelemetry);		
		
	}

	@Override
	public SensorTelemetry saveStatus(SensorTelemetry sensorTelemetry, String code) throws Exception {
		SensorStatus sensorStatus = sensorStatusDao.getByCode(code);
		
		if (sensorStatus != null) {
			sensorTelemetry.setSensorStatus(sensorStatus);
			
			return this.save(sensorTelemetry);
		}
		
		return sensorTelemetry;
	}

	@Override
	public List<SensorTelemetry> getAllActive() throws Exception {
		return this.sensorTelemetryDao.getAllActive();
	}
}
