package com.thingtrack.konekti.dao.impl.internal;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.api.ServiceDao;
import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.domain.EmployeeAgent;
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

	@SuppressWarnings("unchecked")
	public List<Service> getAllPlanned(Organization organization)
			throws Exception {
		String queryString = "SELECT DISTINCT sv";
		queryString += " FROM Service sv";
		queryString += " JOIN sv.routes rt";
		queryString += " WHERE sv.organization = :organization";
		queryString += " AND EXISTS (SELECT wl FROM WorksheetLine wl WHERE wl.service = sv)";

		Query query = (Query) getEntityManager().createQuery(queryString)
				.setParameter("organization", organization);

		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Service> getAllPlanned(Organization organization,
			EmployeeAgent employeeAgent) throws Exception {
		String queryString = "SELECT DISTINCT sv";
		queryString += " FROM Service sv";
		queryString += " JOIN sv.routes rt";
		queryString += " WHERE sv.organization = :organization";
		queryString += " AND rt.driver = :driver";
		queryString += " AND EXISTS (SELECT wl FROM WorksheetLine wl WHERE wl.service = sv)";

		Query query = (Query) getEntityManager().createQuery(queryString)
				.setParameter("organization", organization)
				.setParameter("driver", employeeAgent);

		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Service> getAllNonPlanned(Organization organization)
			throws Exception {
		String queryString = "SELECT DISTINCT sv";
		queryString += " FROM Service sv";
		queryString += " JOIN sv.routes rt";
		queryString += " WHERE sv.organization = :organization";
		queryString += " AND NOT EXISTS (SELECT wl FROM WorksheetLine wl WHERE wl.service = sv)";

		Query query = (Query) getEntityManager().createQuery(queryString)
				.setParameter("organization", organization);

		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Service> getAllNonPlanned(Organization organization,
			EmployeeAgent employeeAgent) throws Exception {
		String queryString = "SELECT DISTINCT sv";
		queryString += " FROM Service sv";
		queryString += " JOIN sv.routes rt";
		queryString += " WHERE sv.organization = :organization";
		queryString += " AND rt.driver = :driver";
		queryString += " AND NOT EXISTS (SELECT wl FROM WorksheetLine wl WHERE wl.service = sv)";

		Query query = (Query) getEntityManager().createQuery(queryString)
				.setParameter("organization", organization)
				.setParameter("driver", employeeAgent);

		return query.getResultList();

	}

	@SuppressWarnings("unused")
	private Date getNowDate() {
		Date date = new Date();

		// Get Calendar object set to the date and time of the given Date object
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		// Set time fields to zero
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		// Put it back in the Date object
		return cal.getTime();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Service> getAllNonPlanned(Organization organization,
			Date routeStartDate) throws Exception {

		String queryString = "SELECT  sv";
		queryString += " FROM Service sv";
		queryString += " JOIN sv.routes rt";
		queryString += " LEFT JOIN sv.worksheetLines wrkl";
		queryString += " JOIN rt.stops stp";
		queryString += " WHERE sv.organization = :organization";
		queryString += " AND (rt.driver IS NULL OR rt.vehicle IS NULL)";
		queryString += " AND stp.stopCheckoutDate >= :routeStartDate and stp.stopCheckoutDate < :routeStartNextDate";
		// queryString +=
		// " AND NOT EXISTS (SELECT wl FROM WorksheetLine wl WHERE wl = wrkl)";

		Query query = (Query) getEntityManager()
				.createQuery(queryString)
				.setParameter("organization", organization)
				.setParameter("routeStartDate", routeStartDate,
						TemporalType.DATE)
				.setParameter("routeStartNextDate", getNext(routeStartDate),
						TemporalType.DATE);

		return query.getResultList();
	}

	private Date getNext(Date routeStartDate) {

		GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar
				.getInstance();
		calendar.setTime(routeStartDate);

		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Service> getAllPlanned(Organization organization,
			EmployeeAgent employeeAgent, Date routeStartDate) throws Exception {

		String queryString = "SELECT DISTINCT sv";
		queryString += " FROM Service sv";
		queryString += " JOIN sv.routes rt";
		queryString += " JOIN rt.stops stp";
		queryString += " WHERE sv.organization = :organization";
		queryString += " AND rt.driver = :driver";
		queryString += " AND stp.stopCheckoutDate >= :routeStartDate and stp.stopCheckoutDate < :routeStartNextDate";
		queryString += " AND EXISTS (SELECT wl FROM WorksheetLine wl WHERE wl.service = sv)";

		Query query = (Query) getEntityManager()
				.createQuery(queryString)
				.setParameter("organization", organization)
				.setParameter("driver", employeeAgent)
				.setParameter("routeStartDate", routeStartDate,
						TemporalType.DATE)
				.setParameter("routeStartNextDate", getNext(routeStartDate),
						TemporalType.DATE);

		return query.getResultList();

	}

	@Override
	public List<Service> getAllNoTurnAssigned(Organization organization)
			throws Exception {

		String queryString = "SELECT DISTINCT sv";
		queryString += " FROM Service sv";
		queryString += " JOIN sv.routes rt";
		queryString += " JOIN rt.stops stp";
		queryString += " WHERE sv.organization = :organization";
		queryString += " AND NOT EXISTS (SELECT tr FROM Turn tr WHERE tr.organization = :organization AND sv MEMBER OF tr.services)";

		Query query = (Query) getEntityManager().createQuery(queryString)
				.setParameter("organization", organization);

		return query.getResultList();

	}

}
