package com.thingtrack.konekti.dao.impl.internal;

import com.thingtrack.konekti.dao.api.ConfigurationDao;
import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.domain.Configuration;
import com.thingtrack.konekti.domain.MenuResource;
import com.thingtrack.konekti.domain.Organization;

public class ConfigurationDaoImpl extends JpaDao<Configuration, Integer> implements ConfigurationDao {

	@Override
	public Configuration getByTag(String tag) throws Exception {
		Configuration configuration = (Configuration)getEntityManager()
		.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.tag = :tag")
		.setParameter("tag", tag).getSingleResult();

		return configuration;
		
	}

	@Override
	public Configuration getByTag(String tag, Organization organization, MenuResource menuResource) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
