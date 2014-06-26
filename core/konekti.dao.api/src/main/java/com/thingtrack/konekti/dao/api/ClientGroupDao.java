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
import com.thingtrack.konekti.domain.ClientGroup;
import com.thingtrack.konekti.domain.Organization;

/**
 * {@link CalendarGroup} Data Access Layer
 * <p>
 * @author Thingtrack S.L.
 *
 */
public interface ClientGroupDao extends Dao<ClientGroup, Integer> {
	
	public List<ClientGroup> getAll(Organization organization) throws Exception;
	
	/**
	 * Obtains an {@link ClientGroup} by its {@code name}
	 * 
	 * @param name  the unique code, not null
	 * @return the {@code ClientGroup}
	 * @throws Exception if there is no {@code ClientGroup} associated to the passed name or it is null
	 */
	public ClientGroup getByName(Organization organization, String name ) throws Exception;
	
}
