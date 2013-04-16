package com.thingtrack.konekti.dao.impl.internal;

import java.util.List;

import javax.persistence.Query;

import com.thingtrack.konekti.dao.api.ConfigurationDao;
import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.domain.Configuration;
import com.thingtrack.konekti.domain.MenuResource;
import com.thingtrack.konekti.domain.User;

public class ConfigurationDaoImpl extends JpaDao<Configuration, Integer> implements ConfigurationDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Configuration> getAll(User user) throws Exception {
		StringBuffer queryString = new StringBuffer("SELECT p FROM " + getEntityName() + " p");
		
		if (user.getActiveOrganization() != null)
			queryString.append( " WHERE (p.organization IS NULL OR p.organization = :organization)");
		else
			queryString.append( " WHERE p.organization IS NULL");
		
		Query query = (Query) getEntityManager().createQuery(queryString.toString());
		
		if (user.getActiveOrganization() != null)
			query.setParameter("organization", user.getActiveOrganization());
		
		return query.getResultList();

	}
	
	@Override
	public Configuration getByTag(String tag) throws Exception {
		Configuration configuration = (Configuration)getEntityManager()
		.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.tag = :tag")
		.setParameter("tag", tag).getSingleResult();

		return configuration;
		
	}
	
	@Override
	public Configuration getByTag(User user, String tag, MenuResource menuResource) throws Exception {
		StringBuffer queryString = new StringBuffer("SELECT p FROM " + getEntityName() + " p");
		
		queryString.append( " WHERE p.tag = :tag");
		
		if (user.getActiveOrganization() != null)
			queryString.append( " AND (p.organization IS NULL OR p.organization = :organization)");
			
		Query query = (Query) getEntityManager().createQuery(queryString.toString());
		
		query.setParameter("tag", tag);
		
		if (user.getActiveOrganization() != null)
			query.setParameter("organization", user.getActiveOrganization());
		
		return (Configuration) query.getSingleResult();
	}

}
