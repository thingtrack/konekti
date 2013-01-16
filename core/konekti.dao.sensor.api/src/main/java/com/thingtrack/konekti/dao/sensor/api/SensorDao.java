package com.thingtrack.konekti.dao.sensor.api;

import java.util.List;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.sensor.Sensor;

public interface SensorDao extends Dao<Sensor, Integer> {
	public Sensor getByCode(String code) throws Exception;
	public Sensor getByMac(String mac) throws Exception;
	public List<Sensor> getAllActive() throws Exception;
}
