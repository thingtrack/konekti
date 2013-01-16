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
package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.Address;

/**
 * @author Thingtrack S.L.
 *
 */
public interface AddressService {
	public List<Address> getAll() throws Exception;
	public Address get( Integer addressId ) throws Exception;
	public Address getByStreet( String street ) throws Exception;
	public Address getByLongitudeLatitude( double longitude, double latitude ) throws Exception;
	public Address save(Address address) throws Exception;
	public void delete(Address address) throws Exception;
}
