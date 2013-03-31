package com.thingtrack.konekti.dao.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.JobTriggerType;

public interface JobTriggerTypeDao extends Dao<JobTriggerType, Integer> {
	public JobTriggerType getByCode(String code) throws Exception;
}
