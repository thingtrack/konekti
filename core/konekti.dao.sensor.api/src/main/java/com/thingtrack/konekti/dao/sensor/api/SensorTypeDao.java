package com.thingtrack.konekti.dao.sensor.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.sensor.SensorType;

public interface SensorTypeDao extends Dao<SensorType, Integer> {
	public SensorType getByCode(String code) throws Exception;
}
