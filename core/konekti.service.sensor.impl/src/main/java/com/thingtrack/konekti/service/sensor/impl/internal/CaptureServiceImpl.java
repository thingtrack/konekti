package com.thingtrack.konekti.service.sensor.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.sensor.api.CaptureDao;
import com.thingtrack.konekti.domain.sensor.Capture;
import com.thingtrack.konekti.service.sensor.api.CaptureService;

public class CaptureServiceImpl implements CaptureService {
	@Autowired
	private CaptureDao captureDao;
	
	public List<Capture> getAll() throws Exception {
		return this.captureDao.getAll();
		
	}

	public Capture get(Integer actionId) throws Exception {
		return this.captureDao.get(actionId);
		
	}

	public Capture save(Capture capture) throws Exception {
		return this.captureDao.save(capture);
		
	}

	public void delete(Capture capture) throws Exception {
		this.captureDao.delete(capture);
		
	}

}
