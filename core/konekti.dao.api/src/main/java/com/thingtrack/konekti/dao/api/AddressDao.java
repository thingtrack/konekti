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

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.Address;

/**
 * Address Data Access Object
 * <p>
 * @author Thingtrack S.L.
 *
 */
public interface AddressDao extends Dao<Address, Integer> {
	
	/**
	 * Obtains an {@code Address} object by its street property
	 *  
	 * @param street property to be filtered, not null 
	 * @return the searched {@code Address} object, not null
	 * @throws Exception if the street parameter does not belongs to any {@code Address} or it is null
	 */
	public Address getByStreet( String street ) throws Exception;
	
	/**
	 * Obtains an {@code Address} object by its coordinates
	 * 
	 * @param longitude
	 * @param latitude
	 * @return the searched {@code Address } object, not null
	 * @throws Exception if the coordinates do not belongs to any {@code Address } or any is null 
	 */
	public Address getByLongitudeLatitude( double longitude, double latitude ) throws Exception;

}
