package com.thingtrack.konekti.dao.impl.internal;

import java.sql.Connection;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.api.ReportDao;
import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.Report;
import com.thingtrack.konekti.domain.User;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class ReportDaoImpl extends JpaDao<Report, Integer> implements ReportDao {
	@Override
	public Report getByCode(Organization organization, String code) throws Exception {
		StringBuffer queryString = new StringBuffer("SELECT p FROM " + getEntityName() + " p WHERE p.code = :code");
				
		if (organization != null)
			queryString.append(" AND p.organization = :organization");
		
		Query query = (Query) getEntityManager().createQuery(queryString.toString());
		
		query.setParameter("code", code);
				
		if (organization != null)
			query.setParameter("organization", organization);

		return (Report)query.getSingleResult();
	}
		
	@SuppressWarnings("unchecked")
	@Override
	public List<Report> getAll(User user) throws Exception {
		StringBuffer queryString = new StringBuffer("SELECT p FROM " + getEntityName() + " p");

		if (user.getActiveOrganization() != null)
			queryString.append(" WHERE p.organization = :organization");

		Query query = (Query) getEntityManager().createQuery(queryString.toString());
		
		if (user.getActiveOrganization() != null)
			query.setParameter("organization", user.getActiveOrganization());
		
		return query.getResultList();
	}
	
	@Override
	public Connection getConnection() throws Exception {
		return getEntityManager().unwrap(java.sql.Connection.class);
		
	}
}
