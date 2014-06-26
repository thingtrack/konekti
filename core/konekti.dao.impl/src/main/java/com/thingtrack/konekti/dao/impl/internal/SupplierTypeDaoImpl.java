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

import java.util.List;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.dao.api.SupplierTypeDao;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.SupplierType;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class SupplierTypeDaoImpl extends JpaDao<SupplierType, Integer> implements SupplierTypeDao {
	@SuppressWarnings("unchecked")
	@Override
	public List<SupplierType> getAll(Organization organization) throws Exception {
		return (List<SupplierType>)getEntityManager()
				.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.organization = :organization")
				.setParameter("organization", organization).getResultList();
		
	}
	
	@Override
	public SupplierType getByName(Organization organization, String name) throws Exception {
		SupplierType supplierType = (SupplierType)getEntityManager()
				.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.organization = :organization AND p.name = :name")
				.setParameter("organization", organization)
				.setParameter("name", name).getSingleResult();

		return supplierType;
		
	}

}
