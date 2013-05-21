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
import com.thingtrack.konekti.domain.Sequence;
import com.thingtrack.konekti.domain.Supplier;
import com.thingtrack.konekti.domain.User;
import com.thingtrack.konekti.dao.api.SupplierDao;
import com.thingtrack.konekti.service.api.SequenceService;
import com.thingtrack.konekti.service.api.SupplierService;

/**
 * @author Thingtrack S.L.
 *
 */
public class SupplierServiceImpl implements SupplierService {
	@Autowired
	private SupplierDao supplierDao;

	@Autowired
	private SequenceService sequenceService;
	
	@Override
	public List<Supplier> getAll() throws Exception {
		return this.supplierDao.getAll();
		
	}

	@Override
	public Supplier get(Integer supplierId) throws Exception {
		return this.supplierDao.get(supplierId);
		
	}

	@Override
	public Supplier getByCode(String code) throws Exception {
		return this.supplierDao.getByCode(code);
		
	}

	@Override
	public Supplier save(Supplier supplier) throws Exception {
		return this.supplierDao.save(supplier);
		
	}

	@Override
	public void delete(Supplier supplier) throws Exception {
		this.supplierDao.delete(supplier);
		
	}
	
	@Override
	public Supplier createNewSupplier(Organization organization) throws Exception {
		Supplier supplier = new Supplier();
		
		supplier.setCode(sequenceService.setNextSequence(Sequence.CODE.SUPPLIER.name()));
		supplier.setOrganization(organization);
		supplier.setActive(true);
		
		return supplier;
	}

	@Override
	public Supplier getByUser(User user) throws Exception {
		return this.supplierDao.getByUser(user);
		
	}
	
	public List<Supplier> getAll(User user) throws Exception {
		return this.supplierDao.getAll(user);
	}
	
	@Override
	public List<Supplier> getAll(User user, boolean active) throws Exception {
		return this.supplierDao.getAll(user, active);
	}
}
