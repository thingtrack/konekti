package com.thingtrack.konekti.dao.sensor.api;

import java.util.List;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.sensor.SensorLocation;

public interface SensorLocationDao  extends Dao<SensorLocation, Integer> {
	public SensorLocation getByCode(String code) throws Exception;
	public SensorLocation getByMac(String mac) throws Exception;
	public List<SensorLocation> getAllActive() throws Exception;
}
