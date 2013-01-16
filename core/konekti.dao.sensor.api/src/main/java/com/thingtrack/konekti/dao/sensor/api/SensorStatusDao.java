package com.thingtrack.konekti.dao.sensor.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.sensor.SensorStatus;

public interface SensorStatusDao extends Dao<SensorStatus, Integer> {
	public SensorStatus getByCode(String code) throws Exception;
}
