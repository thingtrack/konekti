package com.thingtrack.konekti.dao.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.AlarmJob;

public interface AlarmJobDao extends Dao<AlarmJob, Integer> {
	public AlarmJob getByGroupName(String group, String name) throws Exception;
}
