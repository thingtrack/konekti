package com.thingtrack.konekti.dao.api;

import java.util.List;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.Application;
import com.thingtrack.konekti.domain.User;

public interface ApplicationDao extends Dao<Application, Integer> {
	public Application getByName(String name) throws Exception;
	public List<Application> getAll(User user) throws Exception;
}