package com.thingtrack.konekti.service.sensor.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.sensor.api.SensorMessageDao;
import com.thingtrack.konekti.dao.sensor.api.SensorStatusDao;
import com.thingtrack.konekti.service.sensor.api.SensorMessageService;
import com.thingtrack.konekti.domain.sensor.SensorMessage;
import com.thingtrack.konekti.domain.sensor.SensorStatus;

public class SensorMessageServiceImpl implements SensorMessageService {
	@Autowired
	private SensorMessageDao sensorMessageDao;

	@Autowired
	private SensorStatusDao sensorStatusDao;
	
	@Override
	public List<SensorMessage> getAll() throws Exception {
		return this.sensorMessageDao.getAll();
		
	}

	@Override
	public SensorMessage get(Integer sensorMessageId) throws Exception {
		return this.sensorMessageDao.get(sensorMessageId);
		
	}

	@Override
	public SensorMessage getByCode(String code) throws Exception {
		return this.sensorMessageDao.getByCode(code);
		
	}

	@Override
	public SensorMessage getByMac(String mac) throws Exception {
		return this.sensorMessageDao.getByMac(mac);
		
	}

	@Override
	public SensorMessage save(SensorMessage sensorMessage) throws Exception {
		return this.sensorMessageDao.save(sensorMessage);
		
	}

	@Override
	public void SensorMessage(SensorMessage sensorMessage) throws Exception {
		this.sensorMessageDao.delete(sensorMessage);
		
	}

	@Override
	public SensorMessage saveStatus(SensorMessage sensorMessage, String code) throws Exception {
		SensorStatus sensorStatus = sensorStatusDao.getByCode(code);
		
		if (sensorStatus != null) {
			sensorMessage.setSensorStatus(sensorStatus);
			
			return this.save(sensorMessage);
		}
		
		return sensorMessage;
	}
	
	@Override
	public List<SensorMessage> getAllActive() throws Exception {
		return this.sensorMessageDao.getAllActive();
		
	}
}
