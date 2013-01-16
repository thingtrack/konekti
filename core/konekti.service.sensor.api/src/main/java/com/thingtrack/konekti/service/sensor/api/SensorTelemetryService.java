package com.thingtrack.konekti.service.sensor.api;

import java.util.List;

import com.thingtrack.konekti.domain.sensor.SensorTelemetry;

/**
 * @author Thingtrack S.L.
 *
 */
public interface SensorTelemetryService {
	public List<SensorTelemetry> getAll() throws Exception;
	public SensorTelemetry get( Integer sensorTelemetryId ) throws Exception;
	public SensorTelemetry save(SensorTelemetry sensorTelemetry) throws Exception;
	public void delete(SensorTelemetry sensorTelemetry) throws Exception;
	
	public SensorTelemetry saveStatus(SensorTelemetry sensorTelemetry, String code) throws Exception;
	public SensorTelemetry getByCode( String code ) throws Exception;
	public SensorTelemetry getByMac( String mac ) throws Exception;
	public List<SensorTelemetry> getAllActive() throws Exception;
}
