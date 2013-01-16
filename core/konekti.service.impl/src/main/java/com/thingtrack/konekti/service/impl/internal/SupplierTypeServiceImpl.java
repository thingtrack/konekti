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

import com.thingtrack.konekti.domain.SupplierType;
import com.thingtrack.konekti.dao.api.SupplierTypeDao;
import com.thingtrack.konekti.service.api.SupplierTypeService;

/**
 * @author Thingtrack S.L.
 *
 */
public class SupplierTypeServiceImpl implements SupplierTypeService {
	@Autowired
	private SupplierTypeDao supplierTypeDao;

	@Override
	public List<SupplierType> getAll() throws Exception {
		return this.supplierTypeDao.getAll();
		
	}

	@Override
	public SupplierType get(Integer supplierId) throws Exception {
		return this.supplierTypeDao.get(supplierId);
		
	}

	@Override
	public SupplierType getByName(String name) throws Exception {
		return this.supplierTypeDao.getByName(name);
		
	}

	@Override
	public SupplierType save(SupplierType supplierType) throws Exception {
		return this.supplierTypeDao.save(supplierType);
		
	}

	@Override
	public void delete(SupplierType supplierType) throws Exception {
		this.supplierTypeDao.delete(supplierType);
		
	}

}
