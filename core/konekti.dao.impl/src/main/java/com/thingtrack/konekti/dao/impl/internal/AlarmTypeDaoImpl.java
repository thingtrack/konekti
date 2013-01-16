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
import com.thingtrack.konekti.dao.api.AlarmTypeDao;
import com.thingtrack.konekti.domain.AlarmType;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class AlarmTypeDaoImpl extends JpaDao<AlarmType, Integer> implements AlarmTypeDao {
	@Override
	public AlarmType getByCode(String code) throws Exception {
		AlarmType alarmType = (AlarmType)getEntityManager()
				.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.code = :code")
				.setParameter("code", code).getSingleResult();

		return alarmType;
		
	}

}
