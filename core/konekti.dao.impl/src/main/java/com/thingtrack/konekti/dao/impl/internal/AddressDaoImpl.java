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
package com.thingtrack.konekti.dao.impl.internal;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.dao.api.AddressDao;
import com.thingtrack.konekti.domain.Address;

/**
 * @author Thingtrack S.L.
 * 
 */
@Repository
public class AddressDaoImpl extends JpaDao<Address, Integer> implements AddressDao {
	@Override
	public Address getByStreet(String street) throws Exception {
		Address address = (Address) getEntityManager()
				.createQuery(
						"SELECT p FROM " + getEntityName()
								+ " p WHERE p.street = :street")
				.setParameter("street", street).getSingleResult();

		return address;

	}

	@Override
	public Address getByLongitudeLatitude(double longitude, double latitude)
			throws Exception {

		Address address = (Address) getEntityManager()
				.createQuery(
						"SELECT p FROM "
								+ getEntityName()
								+ " p WHERE p.longitude = :longitude AND p.latitude = :latitude")
				.setParameter("longitude", longitude)
				.setParameter("latitude", latitude).getSingleResult();

		return address;

	}
}
