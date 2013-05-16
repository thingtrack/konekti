package com.thingtrack.konekti.dao.api;

import java.util.List;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.Job;
import com.thingtrack.konekti.domain.User;

public interface JobDao extends Dao<Job, Integer> {
	public List<Job> getAll(User user) throws Exception;
	public Job getByGroupName(String group, String name) throws Exception;
}
