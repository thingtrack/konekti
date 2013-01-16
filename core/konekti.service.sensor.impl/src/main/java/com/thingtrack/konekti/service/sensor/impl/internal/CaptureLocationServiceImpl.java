package com.thingtrack.konekti.service.sensor.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.sensor.api.CaptureLocationDao;
import com.thingtrack.konekti.domain.sensor.CaptureLocation;
import com.thingtrack.konekti.service.sensor.api.CaptureLocationService;

public class CaptureLocationServiceImpl implements CaptureLocationService {
	@Autowired
	private CaptureLocationDao captureLocationDao;
	
	public List<CaptureLocation> getAll() throws Exception {
		return this.captureLocationDao.getAll();
	}

	public CaptureLocation get(Integer actionId) throws Exception {
		return this.captureLocationDao.get(actionId);
		
	}

	public CaptureLocation save(CaptureLocation locationCapture)
			throws Exception {
		return this.captureLocationDao.save(locationCapture);
		
	}

	public void delete(CaptureLocation locationCapture) throws Exception {
		this.captureLocationDao.delete(locationCapture);
		
	}

}
