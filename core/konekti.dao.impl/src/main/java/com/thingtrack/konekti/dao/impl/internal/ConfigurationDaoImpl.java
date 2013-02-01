package com.thingtrack.konekti.dao.impl.internal;

import com.thingtrack.konekti.dao.api.ConfigurationDao;
import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.domain.Configuration;

public class ConfigurationDaoImpl extends JpaDao<Configuration, Integer> implements ConfigurationDao {

	@Override
	public Configuration getByCode(String code) throws Exception {
		Configuration configuration = (Configuration)getEntityManager()
		.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.code = :code")
		.setParameter("code", code).getSingleResult();

		return configuration;
		
	}

}
