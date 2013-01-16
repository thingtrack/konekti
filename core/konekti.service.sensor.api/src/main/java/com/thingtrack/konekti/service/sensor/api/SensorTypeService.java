package com.thingtrack.konekti.service.sensor.api;

import java.util.List;

import com.thingtrack.konekti.domain.sensor.SensorType;

/**
 * @author Thingtrack S.L.
 *
 */
public interface SensorTypeService {
	public List<SensorType> getAll() throws Exception;
	public SensorType get( Integer sensorTypeId ) throws Exception;
	public SensorType getByCode( String code ) throws Exception;
	public SensorType save(SensorType sensorType) throws Exception;
	public void delete(SensorType sensorType) throws Exception;
}
