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

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.dao.api.UserDao;
import com.thingtrack.konekti.domain.User;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class UserDaoImpl extends JpaDao<User, Integer> implements UserDao {
	@Override
	public User getByUsername(String username) throws Exception {
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT p FROM " + getEntityName() + " p");
//		buffer.append(" LEFT JOIN p.client c");
//		buffer.append(" LEFT JOIN p.employeeAgent e");
//		buffer.append(" LEFT JOIN p.supplier s");
		buffer.append(" WHERE p.username = :username");
		
		
		User user = (User)getEntityManager()
				.createQuery(buffer.toString())
				.setParameter("username", username).getSingleResult();

		return user;
		
	}	
	
}
