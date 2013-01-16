package com.thingtrack.konekti.dao.sensor.api;

import java.util.List;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.sensor.SensorMessage;

public interface SensorMessageDao extends Dao<SensorMessage, Integer> {
	public SensorMessage getByCode(String code) throws Exception;
	public SensorMessage getByMac(String mac) throws Exception;
	public List<SensorMessage> getAllActive() throws Exception;
}
