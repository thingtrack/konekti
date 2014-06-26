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

import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.SupplierGroup;
import com.thingtrack.konekti.dao.api.SupplierGroupDao;
import com.thingtrack.konekti.service.api.SupplierGroupService;

/**
 * @author Thingtrack S.L.
 *
 */
public class SupplierGroupServiceImpl implements SupplierGroupService {
	@Autowired
	private SupplierGroupDao supplierGroupDao;

	@Override
	public List<SupplierGroup> getAll(Organization organization) throws Exception {
		return this.supplierGroupDao.getAll(organization);
		
	}

	@Override
	public SupplierGroup get(Integer supplierAgentId) throws Exception {
		return this.supplierGroupDao.get(supplierAgentId);
		
	}

	@Override
	public SupplierGroup getByname(Organization organization, String name) throws Exception {
		return this.supplierGroupDao.getByName(organization, name);
		
	}

	@Override
	public SupplierGroup save(SupplierGroup supplierGroup) throws Exception {
		return this.supplierGroupDao.save(supplierGroup);
		
	}

	@Override
	public void delete(SupplierGroup supplierGroup) throws Exception {
		this.supplierGroupDao.delete(supplierGroup);
		
	}

}
