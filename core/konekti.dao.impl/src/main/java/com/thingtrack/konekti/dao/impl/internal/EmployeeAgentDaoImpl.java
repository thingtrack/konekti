package com.thingtrack.konekti.dao.impl.internal;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.api.EmployeeAgentDao;
import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.domain.Area;
import com.thingtrack.konekti.domain.EmployeeAgent;
import com.thingtrack.konekti.domain.EmployeeAgentType;
import com.thingtrack.konekti.domain.Location;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.User;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class EmployeeAgentDaoImpl extends JpaDao<EmployeeAgent, Integer> implements EmployeeAgentDao {
	@Override
	public EmployeeAgent getByName(Organization organization, String name) throws Exception {
		
		String queryString = "SELECT em";
		queryString += " FROM " + getEntityName() + " em";
		queryString += " JOIN  em.organizations org";
		queryString += " WHERE em.name = :name";
		queryString += " AND EXISTS (SELECT org FROM em.organizations org WHERE org = :organization)";
		
		
		Query query = (Query) getEntityManager().createQuery(queryString)
			.setParameter("name", name)
			.setParameter("organization", organization);

		return (EmployeeAgent) query.getSingleResult();

	}

	@Override
	public EmployeeAgent getByUser(User user) throws Exception {
		
		String queryString = "SELECT em";
		queryString += " FROM " + getEntityName() + " em";
		queryString += " WHERE em.user = :user";
		
		Query query = (Query) getEntityManager().createQuery(queryString)
		.setParameter("user", user);
		
		return (EmployeeAgent) query.getSingleResult();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeAgent> getByType(Organization organization, EmployeeAgentType employeeAgentType) throws Exception {
		
		String queryString = "SELECT em";
		queryString += " FROM " + getEntityName() + " em";
		queryString += " WHERE em.employeeAgentType = :type";
		queryString += " AND EXISTS (SELECT org FROM em.organizations org WHERE org = :organization)";

		Query query = (Query) getEntityManager().createQuery(queryString)
		.setParameter("type", employeeAgentType)
		.setParameter("organization", organization);

		return query.getResultList();
	}

	@Override
	public EmployeeAgent getByWorkNumber(Organization organization, String workNumber) throws Exception {
		String queryString = "SELECT em";
		queryString += " FROM " + getEntityName() + " em";
		queryString += " JOIN  em.organizations org";
		queryString += " WHERE em.workNumber = :workNumber";
		
		Query query = (Query) getEntityManager().createQuery(queryString)
		.setParameter("workNumber", workNumber);
		
		return (EmployeeAgent) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Location> getAllLocationByOrganization(Organization organization, int agentId) throws Exception {
		
		String queryString = "SELECT lc";
		queryString += " FROM " + getEntityName() + " em";
		queryString += " JOIN em.locations lc";
		queryString += " JOIN lc.organizations org";
		queryString += " WHERE org.organizationId = :organizationId";
		queryString += " AND em.agentId = :agentId";

		Query query = (Query) getEntityManager().createQuery(queryString)
		.setParameter("organizationId", organization.getOrganizationId())
		.setParameter("agentId", agentId);

		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Area> getAllAreaByLocation(Location location, int agentId) throws Exception {
		
		String queryString = "SELECT ar";
		queryString += " FROM " + getEntityName() + " em";
		queryString += " JOIN em.areas ar";
		queryString += " JOIN ar.location lc";
		queryString += " WHERE lc.locationId = :locationId";
		queryString += " AND em.agentId = :agentId";

		Query query = (Query) getEntityManager().createQuery(queryString)
		.setParameter("locationId", location.getLocationId())
		.setParameter("agentId", agentId);

		return query.getResultList();
	}
}
