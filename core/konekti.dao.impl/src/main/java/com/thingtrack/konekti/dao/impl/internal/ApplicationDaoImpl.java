package com.thingtrack.konekti.dao.impl.internal;

import java.util.List;

import javax.persistence.Query;

import com.thingtrack.konekti.dao.api.ApplicationDao;
import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.domain.Application;
import com.thingtrack.konekti.domain.User;

public class ApplicationDaoImpl extends JpaDao<Application, Integer> implements ApplicationDao {

	@Override
	public Application getByName(String applicationType) throws Exception {
		Application application = (Application)getEntityManager()
				.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.applicationType = :applicationType")
				.setParameter("applicationType", Application.TYPE.Web).getSingleResult();

		return application;
	}

	@Override
	public Application getByType(Application.TYPE applicationType) throws Exception {
		Application application = (Application)getEntityManager()
				.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.applicationType = :applicationType")
				.setParameter("applicationType", applicationType).getSingleResult();

		return application;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Application> getAll(User user) throws Exception {
		StringBuffer queryString = new StringBuffer("SELECT p FROM " + getEntityName() + " p");

		if (user.getActiveOrganization() != null)
			queryString.append(" WHERE p.organization = :organization");

		Query query = (Query) getEntityManager().createQuery(queryString.toString());
		
		if (user.getActiveOrganization() != null)
			query.setParameter("organization", user.getActiveOrganization());
		
		return query.getResultList();
	}
	
}
