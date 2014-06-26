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
package com.thingtrack.konekti.dao.api;

import java.util.List;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.CalendarGroup;
import com.thingtrack.konekti.domain.ClientType;
import com.thingtrack.konekti.domain.Organization;

/**
 * {@link CalendarGroup} Data Access Layer
 * <p>
 * @author Thingtrack S.L.
 *
 */
public interface ClientTypeDao extends Dao<ClientType, Integer> {
	
	public List<ClientType> getAll(Organization organization) throws Exception;
	
	/**
	 * Obtains an {@link ClientType} by its code
	 * 
	 * @param code  the unique code to filter the ClientType, not null
	 * @return {@code ClientType} which the passed {@code code} belongs to
	 * @throws Exception if the the {@code code} no belongs to any {@code ClientType} or it is null
	 */
	public ClientType getByCode(Organization organization, String code) throws Exception;
	
}
