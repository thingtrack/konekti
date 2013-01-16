package com.thingtrack.konekti.dao.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.AlarmTriggerType;

public interface AlarmTriggerTypeDao extends Dao<AlarmTriggerType, Integer> {
	public AlarmTriggerType getByCode(String code) throws Exception;
}
