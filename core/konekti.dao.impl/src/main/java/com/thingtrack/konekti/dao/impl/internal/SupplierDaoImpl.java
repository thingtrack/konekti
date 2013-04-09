/*
 * Copyright 2011 Thingtrack, S.L.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.thingtrack.konekti.dao.impl.internal;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.dao.api.SupplierDao;
import com.thingtrack.konekti.domain.Area;
import com.thingtrack.konekti.domain.Location;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.Supplier;
import com.thingtrack.konekti.domain.User;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class SupplierDaoImpl extends JpaDao<Supplier, Integer> implements SupplierDao {
	@Override
	public Supplier getByCode(String code) throws Exception {
		Supplier supplier = (Supplier)getEntityManager()
				.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.code = :code")
				.setParameter("code", code).getSingleResult();

		return supplier;
		
	}

	@Override
	public Supplier getByUser(User user) throws Exception {
		
		String queryString = "SELECT p";
		queryString += " FROM " + getEntityName() + " p";
		queryString += " WHERE p.user = :user";
		
		Query query = (Query) getEntityManager().createQuery(queryString)
		.setParameter("user", user);
		
		return (Supplier) query.getSingleResult();

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Location> getAllLocationByOrganization(Organization organization, int agentId) throws Exception {
		
		String queryString = "SELECT lc";
		queryString += " FROM " + getEntityName() + " sp";
		queryString += " JOIN sp.locations lc";
		queryString += " JOIN lc.organizations org";
		queryString += " WHERE org.organizationId = :organizationId";
		queryString += " AND sp.agentId = :agentId";

		Query query = (Query) getEntityManager().createQuery(queryString)
		.setParameter("organizationId", organization.getOrganizationId())
		.setParameter("agentId", agentId);

		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Area> getAllAreaByLocation(Location location, int agentId) throws Exception {
		
		String queryString = "SELECT ar";
		queryString += " FROM " + getEntityName() + " sp";
		queryString += " JOIN sp.areas ar";
		queryString += " JOIN ar.location lc";
		queryString += " WHERE lc.locationId = :locationId";
		queryString += " AND sp.agentId = :agentId";

		Query query = (Query) getEntityManager().createQuery(queryString)
		.setParameter("locationId", location.getLocationId())
		.setParameter("agentId", agentId);

		return query.getResultList();
	}
}
