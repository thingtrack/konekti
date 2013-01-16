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
package com.thingtrack.konekti.service.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.domain.Address;
import com.thingtrack.konekti.dao.api.AddressDao;
import com.thingtrack.konekti.service.api.AddressService;

/**
 * @author Thingtrack S.L.
 *
 */
public class AddressServiceImpl implements AddressService {
	@Autowired
	private AddressDao addressDao;

	@Override
	public List<Address> getAll() throws Exception {
		return this.addressDao.getAll();
		
	}

	@Override
	public Address get(Integer addressId) throws Exception {
		return this.addressDao.get(addressId);
		
	}

	@Override
	public Address getByStreet(String street) throws Exception {
		return this.addressDao.getByStreet(street);
		
	}
	
	@Override
	public Address getByLongitudeLatitude(double longitude, double latitude) throws Exception {
		return this.addressDao.getByLongitudeLatitude(longitude, latitude);
	}

	@Override
	public Address save(Address address) throws Exception {
		return this.addressDao.save(address);
		
	}

	@Override
	public void delete(Address address) throws Exception {
		this.addressDao.delete(address);
		
	}


}
