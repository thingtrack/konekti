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
import com.thingtrack.konekti.domain.Location;
import com.thingtrack.konekti.domain.Organization;

/**
 * Location Data Access Layer
 * <p>
 * @author Thingtrack S.L.
 *
 */
public interface LocationDao extends Dao<Location, Integer> {
	/**
	 * Obtains {@link Location} by its name
	 * 
	 * @param name  the name to be queryied
	 * @return @ {@link Location} searched
	 * @throws Exception if there is no location found
	 */
	public Location getByName(String name) throws Exception;
	
	/**
	 * Obtains the collection of {@link Location} which belongs to {@code Organization}
	 * @param organization the filter to be queryied, not null
	 * @return a collection of {@link Location} belongs to the given organization
	 * @throws Exception if the given organization is null
	 */
	public List<Location> getAllByOrganization(Organization organization) throws Exception;
	
}
