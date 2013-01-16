package com.thingtrack.konekti.dao.impl.internal;

import java.sql.Connection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.api.ReportDao;
import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.Report;

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
	
	@Override
	public List<Report> getAllByOrganization(Organization organization)
			throws Exception {
		@SuppressWarnings("unchecked")
		List<Report> reports = (List<Report>)getEntityManager()
				.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.organization = :organization")
				.setParameter("organization", organization).getSingleResult();

		return reports;
	}
	

	@Override
	public Connection getConnection() throws Exception {
		return getEntityManager().unwrap(java.sql.Connection.class);
		
	}
}
