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
import com.thingtrack.konekti.domain.User;

/**
 * Area Data Access Layer
 * <p>
 * @author Thingtrack S.L.
 *
 */
public interface AreaDao extends Dao<Area, Integer> {
	
	/**
	 * Obtains an {@link Area} object found by its {@code code}
	 * 
	 * @param code   the code of the {@code Area}, not null
	 * @return an {@code Area} object, not null
	 * @throws Exception if there is no {@code Area} associated to the give {@code code}
	 */
	public Area getByCode(String code) throws Exception;
	
	/**
	 * Obtains an {@link List<Area>} collections associated to the {@link User}
	 * 
	 * @param user the user to obtain the {@code Areas} associated, not null
	 * @return
	 * @throws Exception
	 */
	public List<Area> getAll(User user) throws Exception;
	
	public List<Area> getAllByOrganization(User user) throws Exception;
}
