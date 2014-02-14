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
import com.thingtrack.konekti.domain.Area;
import com.thingtrack.konekti.domain.Role;

/**
 * {@link Role} Data Access Layer
 * <p>
 * @author Thingtrack S.L.
 *
 */
public interface RoleDao extends Dao<Role, Integer> {
	
	/**
	 * Obtains the {@link Role} by its unique code
	 * 
	 * @param code the unique code, not null
	 * @return {@link Role}
	 * @throws Exception if the given parameter is null
	 */
	public Role getByCode(String code) throws Exception;
	
	/**
	 * Obtains the collection of {@link Role roles} belongs to a {@code area}
	 * 
	 * @param area  the area to look for, not null
	 * @return {@link Role}
	 * @throws Exception if the supplied parameter is null
	 */
	public List<Role> getAll(Area area) throws Exception;
}
