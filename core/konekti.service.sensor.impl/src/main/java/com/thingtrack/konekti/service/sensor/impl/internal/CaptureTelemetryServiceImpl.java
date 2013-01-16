package com.thingtrack.konekti.service.sensor.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.sensor.api.CaptureTelemetryDao;
import com.thingtrack.konekti.domain.sensor.CaptureTelemetry;
import com.thingtrack.konekti.service.sensor.api.CaptureTelemetryService;

public class CaptureTelemetryServiceImpl implements CaptureTelemetryService {
	@Autowired
	private CaptureTelemetryDao captureTelemetryDao;
	
	public List<CaptureTelemetry> getAll() throws Exception {
		return this.captureTelemetryDao.getAll();
		
	}

	public CaptureTelemetry get(Integer actionId) throws Exception {
		return this.captureTelemetryDao.get(actionId);
		
	}

	public CaptureTelemetry save(CaptureTelemetry telemetryCapture)
			throws Exception {
		return this.captureTelemetryDao.save(telemetryCapture);
		
	}

	public void delete(CaptureTelemetry telemetryCapture) throws Exception {
		this.captureTelemetryDao.delete(telemetryCapture);
		
	}

}
