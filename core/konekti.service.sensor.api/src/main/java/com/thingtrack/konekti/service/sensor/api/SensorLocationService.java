package com.thingtrack.konekti.service.sensor.api;

import java.util.List;

import com.thingtrack.konekti.domain.sensor.SensorLocation;

public interface SensorLocationService {
	public List<SensorLocation> getAll() throws Exception;
	public SensorLocation get( Integer sensorLocationId ) throws Exception;
	public SensorLocation save(SensorLocation sensorLocation) throws Exception;
	public void delete(SensorLocation sensorLocation) throws Exception;
	
	public SensorLocation saveStatus(SensorLocation sensorLocation, String code) throws Exception;
	public SensorLocation getByCode( String code ) throws Exception;
	public SensorLocation getByMac( String mac ) throws Exception;
	public List<SensorLocation> getAllActive() throws Exception;
}
