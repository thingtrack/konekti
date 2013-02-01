package com.thingtrack.konekti.dao.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.Configuration;

public interface ConfigurationDao extends Dao<Configuration, Integer> {
	public Configuration getByCode(String code) throws Exception;
}
