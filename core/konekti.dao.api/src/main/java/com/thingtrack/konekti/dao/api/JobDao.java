package com.thingtrack.konekti.dao.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.Job;

public interface JobDao extends Dao<Job, Integer> {
	public Job getByGroupName(String group, String name) throws Exception;
}
