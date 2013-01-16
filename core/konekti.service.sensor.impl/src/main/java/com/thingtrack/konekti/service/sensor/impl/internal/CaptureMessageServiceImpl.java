package com.thingtrack.konekti.service.sensor.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.sensor.api.CaptureMessageDao;
import com.thingtrack.konekti.domain.sensor.CaptureMessage;
import com.thingtrack.konekti.service.sensor.api.CaptureMessageService;

public class CaptureMessageServiceImpl implements CaptureMessageService {
	@Autowired
	private CaptureMessageDao captureMessageDao;
	
	@Override
	public List<CaptureMessage> getAll() throws Exception {
		return this.captureMessageDao.getAll();
		
	}

	@Override
	public CaptureMessage get(Integer actionId) throws Exception {
		return this.captureMessageDao.get(actionId);
	}

	@Override
	public CaptureMessage save(CaptureMessage captureMessage) throws Exception {
		return this.captureMessageDao.save(captureMessage);
	}

	@Override
	public void delete(CaptureMessage captureMessage) throws Exception {
		this.captureMessageDao.delete(captureMessage);
		
	}

}
