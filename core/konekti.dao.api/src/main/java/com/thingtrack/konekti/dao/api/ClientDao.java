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
import com.thingtrack.konekti.domain.Client;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.User;

/**
 * Client Data Access Layer
 * 
 * @param code  the unique code, not null
 * @return the {@code Client}
 * @throws Exception if there is no {@code CalendarType} associated t the passed code or code is null
 */
public interface ClientDao extends Dao<Client, Integer> {
	
	/**
	 * Obtains an {@link Client} by its code
	 * 
	 * @param code  the unique code to filter the Client, not null
	 * @return {@code Client} which the passed {@code code} belongs to
	 * @throws Exception if the the {@code code} no belongs to any {@code Client} or is null
	 */
	public Client getByCode(String code) throws Exception;
	
	/**
	 * Obtains an {@link List<Client>} which belogns to the passed {@link Organization} and its {@code code}
	 * 
	 * @param organization the {@code Orgnization} 
	 * @param code  the unique {@code code}
	 * @return Collection of {@link Client clients}
	 * @throws Exception if tje parameters passed not belongs to an {@code CLient}
	 */
	public List<Client> getByCode(Organization organization, String code) throws Exception;
	
	/**
	 * Obtains an {@link Client} associated to its {@link User}
	 *
	 * @param user account associated to a particular Client, not null
	 * @return the {@code Client}
	 * @throws Exception if the user passed is null
	 */
	public Client getByUser(User user) throws Exception;
	
	/**
	 * Obtains a {@code List<Client>} associated the particular {@code User}
	 * 
	 * @param user  the user account, not null
	 * @return the collection of clients
	 * @throws Exception if there is no client associated
	 */
	public List<Client> getAll(User user) throws Exception;
	
	/**
	 * Obtains {@link List<Client>} associated to the {@link User} an also being active or not
	 * 
	 * @param user the user account associated, not null
	 * @param active  the flag to identify is active or not, not null
	 * @return collecttion of clients
	 * @throws Exception if there no result from the query
	 */
	public List<Client> getAll(User user, boolean active) throws Exception;
}
