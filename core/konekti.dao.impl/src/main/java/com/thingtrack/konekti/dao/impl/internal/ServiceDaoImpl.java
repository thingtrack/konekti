package com.thingtrack.konekti.dao.impl.internal;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.api.ServiceDao;
import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.Service;

/**
 * @author Thingtrack S.L.
 * 
 */
@Repository
public class ServiceDaoImpl extends JpaDao<Service, Integer> implements
		ServiceDao {
	@Override
	public Service getByCode(String code) throws Exception {
		// Retrieve existing code
		Service service = (Service) getEntityManager()
				.createQuery(
						"SELECT p FROM " + getEntityName()
								+ " p WHERE p.code = :code")
				.setParameter("code", code).getSingleResult();

		return service;

	}

	@Override
	public List<Service> getAll(Organization organization) throws Exception {
		
		String queryString;
		
		// Retrieve existing code
		queryString = "SELECT DISTINCT sv";
		queryString += " FROM Service sv";
		queryString += " JOIN sv.routes rt";
		queryString += " JOIN rt.stops st";
		queryString += " WHERE sv.organization = :organization";
		
		TypedQuery<Service> query = getEntityManager().createQuery(queryString, Service.class).
				setParameter("organization", organization);
		
		return query.getResultList();
	}

	@Override
	public List<Service> getAllTemplates(Organization organization) throws Exception {
		
		String queryString;
		
		// Retrieve existing code
		queryString = "SELECT DISTINCT sv";
		queryString += " FROM Service sv";
		queryString += " JOIN sv.routes rt";
		queryString += " JOIN rt.stops st";
		queryString += " WHERE sv.organization = :organization";
		
		TypedQuery<Service> query = getEntityManager().createQuery(queryString, Service.class).
				setParameter("organization", organization);
		
		return query.getResultList();
	}

}
