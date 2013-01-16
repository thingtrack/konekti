package com.thingtrack.konekti.service.sensor.api;

import java.util.List;

import com.thingtrack.konekti.domain.sensor.SensorStatus;

/**
 * @author Thingtrack S.L.
 *
 */
public interface SensorStatusService {
	public List<SensorStatus> getAll() throws Exception;
	public SensorStatus get( Integer sensorStatusId ) throws Exception;
	public SensorStatus getByCode( String code ) throws Exception;
	public SensorStatus save(SensorStatus sensorStatus) throws Exception;
	public void delete(SensorStatus sensorStatus) throws Exception;
}
