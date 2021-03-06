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
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.Supplier;
import com.thingtrack.konekti.domain.User;

/**
 * Supplier Data Access Layer
 * <p>
 * @author Thingtrack S.L.
 *
 */
public interface SupplierDao extends Dao<Supplier, Integer> {	
	public List<Supplier> getAll(Organization organization) throws Exception;
	
	/**
	 * The {@link Supplier} by its code
	 * @param code the unique code, not null
	 * @return {@link Supplier}
	 * @throws Exception
	 */
	public Supplier getByCode(Organization organization, String code) throws Exception;
	
	/**
	 * The {@link Supplier} by its user account
	 * @param user the user account, not null
	 * @return {@link Supplier}
	 * @throws Exception if the user is null
	 */
	public Supplier getByUser(User user) throws Exception;
	
	/**
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<Supplier> getAll(User user) throws Exception;
	
	/**
	 * 
	 * @param user
	 * @param active
	 * @return
	 * @throws Exception
	 */
	public List<Supplier> getAll(User user, boolean active) throws Exception;
}
