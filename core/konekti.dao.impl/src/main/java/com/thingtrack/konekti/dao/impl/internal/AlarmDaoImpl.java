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
import com.thingtrack.konekti.dao.api.AlarmDao;
import com.thingtrack.konekti.domain.Alarm;
import com.thingtrack.konekti.domain.User;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class AlarmDaoImpl extends JpaDao<Alarm, Integer> implements AlarmDao {
	@Override
	public Alarm getByMessage(String message) throws Exception {
		Alarm alarm = (Alarm)getEntityManager()
				.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.message = :message")
				.setParameter("message", message).getSingleResult();

		return alarm;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Alarm> getAll(User user) throws Exception {
		StringBuffer queryString = new StringBuffer("SELECT p FROM " + getEntityName() + " p");

		if (user.getActiveOrganization() != null)
			queryString.append(" WHERE p.area = :area");

		Query query = (Query) getEntityManager().createQuery(queryString.toString());
		
		if (user.getActiveArea() != null)
			query.setParameter("area", user.getActiveArea());
		
		queryString.append(" ORDER BY p.alarmDate ASC");
		
		return query.getResultList();
	}
	
}
