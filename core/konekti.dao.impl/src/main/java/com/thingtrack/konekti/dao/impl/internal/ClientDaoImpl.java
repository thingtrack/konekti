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
import com.thingtrack.konekti.dao.api.ClientDao;
import com.thingtrack.konekti.domain.Client;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.User;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class ClientDaoImpl extends JpaDao<Client, Integer> implements ClientDao {
	@Override
	public Client getByCode(String code) throws Exception {
		Client client = (Client)getEntityManager()
				.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.code = :code")
				.setParameter("code", code).getSingleResult();

		return client;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> getAll(User user) throws Exception {
		String queryString =  "SELECT p FROM " + getEntityName() + " p";

		if (user.getActiveArea() != null)
			queryString += " WHERE p.organization = :organization";

		Query query = (Query) getEntityManager().createQuery(queryString);
		
		if (user.getActiveArea() != null)
			query.setParameter("organization", user.getActiveOrganization());
		
		return query.getResultList();
	}
	
	@Override
	public List<Client> getByCode(Organization organization, String code) throws Exception {		
		@SuppressWarnings("unchecked")
		List<Client> clients =  (List<Client>)getEntityManager()
		.createQuery("SELECT p FROM " + getEntityName() + " p WHERE :organization MEMBER OF p.organizations AND p.code LIKE :code")
				.setParameter("organization", (organization != null ? organization : "%"))
				.setParameter("code", (code != null ? code : "%")).getResultList();

		return clients;
		
	}
	
	@Override
	public Client getByUser(User user) throws Exception {
		
		String queryString = "SELECT p";
		queryString += " FROM " + getEntityName() + " p";
		queryString += " WHERE p.user = :user";
		
		Query query = (Query) getEntityManager().createQuery(queryString)
		.setParameter("user", user);
		
		return (Client) query.getSingleResult();

	}
	
}
