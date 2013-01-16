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

import com.thingtrack.konekti.domain.SupplierAgentType;
import com.thingtrack.konekti.dao.api.SupplierAgentTypeDao;
import com.thingtrack.konekti.service.api.SupplierAgentTypeService;

/**
 * @author Thingtrack S.L.
 *
 */
public class SupplierAgentTypeServiceImpl implements SupplierAgentTypeService {
	@Autowired
	private SupplierAgentTypeDao supplierAgentTypeDao;

	@Override
	public List<SupplierAgentType> getAll() throws Exception {
		return this.supplierAgentTypeDao.getAll();
		
	}

	@Override
	public SupplierAgentType get(Integer supplierAgentId) throws Exception {
		return this.supplierAgentTypeDao.get(supplierAgentId);
		
	}

	@Override
	public SupplierAgentType getByCode(String code) throws Exception {
		return this.supplierAgentTypeDao.getByCode(code);
		
	}

	@Override
	public SupplierAgentType save(SupplierAgentType supplierAgentType) throws Exception {
		return this.supplierAgentTypeDao.save(supplierAgentType);
	}

	@Override
	public void delete(SupplierAgentType supplierAgentType) throws Exception {
		this.supplierAgentTypeDao.delete(supplierAgentType);
		
	}

}
