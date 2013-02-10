package com.thingtrack.konekti.dao.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.Configuration;
import com.thingtrack.konekti.domain.MenuResource;
import com.thingtrack.konekti.domain.Organization;

public interface ConfigurationDao extends Dao<Configuration, Integer> {
	public Configuration getByTag(String tag) throws Exception;
	public Configuration getByTag(String tag, Organization organization, MenuResource menuResource) throws Exception;
}
