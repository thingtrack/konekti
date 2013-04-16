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
import com.thingtrack.konekti.dao.api.AreaDao;
import com.thingtrack.konekti.domain.Area;
import com.thingtrack.konekti.domain.User;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class AreaDaoImpl extends JpaDao<Area, Integer> implements AreaDao {
	@Override
	public Area getByCode(String code) throws Exception {
		Area area = (Area)getEntityManager()
				.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.code = :code")
				.setParameter("code", code).getSingleResult();

		return area;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Area> getAll(User user) throws Exception {
		String queryString =  "SELECT p FROM " + getEntityName() + " p";

		if (user.getActiveLocation() != null)
			queryString += " WHERE p.location = :location";

		Query query = (Query) getEntityManager().createQuery(queryString);
		
		if (user.getActiveLocation() != null)
			query.setParameter("location", user.getActiveLocation());
		
		return query.getResultList();
	}
	
}
