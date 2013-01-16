package com.thingtrack.konekti.service.sensor.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.sensor.api.SensorStatusDao;
import com.thingtrack.konekti.domain.sensor.SensorStatus;
import com.thingtrack.konekti.service.sensor.api.SensorStatusService;

public class SensorStatusServiceImpl implements SensorStatusService {
	@Autowired
	private SensorStatusDao sensorStatusDao;
	
	@Override
	public List<SensorStatus> getAll() throws Exception {
		return this.sensorStatusDao.getAll();
	}

	@Override
	public SensorStatus get(Integer sensorStatusId) throws Exception {
		return this.sensorStatusDao.get(sensorStatusId);
		
	}

	@Override
	public SensorStatus getByCode(String code) throws Exception {
		return this.sensorStatusDao.getByCode(code);
		
	}

	@Override
	public SensorStatus save(SensorStatus sensorStatus) throws Exception {
		return this.sensorStatusDao.save(sensorStatus);
		
	}

	@Override
	public void delete(SensorStatus sensorStatus) throws Exception {
		this.sensorStatusDao.delete(sensorStatus);
		
	}

}
