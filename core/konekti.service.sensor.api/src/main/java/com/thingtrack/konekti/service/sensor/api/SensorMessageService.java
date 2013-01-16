package com.thingtrack.konekti.service.sensor.api;

import java.util.List;

import com.thingtrack.konekti.domain.sensor.SensorMessage;

/**
 * @author Thingtrack S.L.
 *
 */
public interface SensorMessageService {
	public List<SensorMessage> getAll() throws Exception;
	public SensorMessage get( Integer sensorMessageId ) throws Exception;
	public SensorMessage save(SensorMessage sensorMessage) throws Exception;
	public void SensorMessage(SensorMessage sensorMessage) throws Exception;
	
	public SensorMessage getByCode( String code ) throws Exception;
	public SensorMessage getByMac( String mac ) throws Exception;
	public SensorMessage saveStatus(SensorMessage sensorMessage, String code) throws Exception;
	public List<SensorMessage> getAllActive() throws Exception;
}
