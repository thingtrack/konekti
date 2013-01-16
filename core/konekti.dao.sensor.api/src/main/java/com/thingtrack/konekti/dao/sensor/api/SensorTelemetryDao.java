package com.thingtrack.konekti.dao.sensor.api;

import java.util.List;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.sensor.SensorTelemetry;

public interface SensorTelemetryDao  extends Dao<SensorTelemetry, Integer> {
	public SensorTelemetry getByCode(String code) throws Exception;
	public SensorTelemetry getByMac(String mac) throws Exception;
	public List<SensorTelemetry> getAllActive() throws Exception;
}
