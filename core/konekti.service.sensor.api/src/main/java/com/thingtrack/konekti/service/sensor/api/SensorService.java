package com.thingtrack.konekti.service.sensor.api;

import java.util.List;

import com.thingtrack.konekti.domain.sensor.Sensor;

/**
 * @author Thingtrack S.L.
 *
 */
public interface SensorService {
	public List<Sensor> getAll() throws Exception;
	public Sensor get( Integer sensorId ) throws Exception;
	public Sensor save(Sensor sensor) throws Exception;
	public void delete(Sensor sensor) throws Exception;
	
	public Sensor getByCode( String code ) throws Exception;
	public Sensor getByMac( String mac ) throws Exception;
	public Sensor saveStatus(Sensor sensor, String code) throws Exception;
	public List<Sensor> getAllActive() throws Exception;
}
