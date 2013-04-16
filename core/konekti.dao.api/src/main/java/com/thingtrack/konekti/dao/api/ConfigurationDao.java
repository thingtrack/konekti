package com.thingtrack.konekti.dao.api;

import java.util.List;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.Configuration;
import com.thingtrack.konekti.domain.MenuResource;
import com.thingtrack.konekti.domain.User;

public interface ConfigurationDao extends Dao<Configuration, Integer> {
	public Configuration getByTag(String tag) throws Exception;
	public List<Configuration> getAll(User user) throws Exception;
	public Configuration getByTag(User user, String tag, MenuResource menuResource) throws Exception;
}
