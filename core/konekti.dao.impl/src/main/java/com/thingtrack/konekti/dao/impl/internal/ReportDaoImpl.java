package com.thingtrack.konekti.dao.impl.internal;

import java.sql.Connection;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.api.ReportDao;
import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.domain.Report;
import com.thingtrack.konekti.domain.User;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class ReportDaoImpl extends JpaDao<Report, Integer> implements ReportDao {
	@Override
	public Report getByCode(String code) throws Exception {
		Report report = (Report)getEntityManager()
				.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.code = :code")
				.setParameter("code", code).getSingleResult();

		return report;
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
