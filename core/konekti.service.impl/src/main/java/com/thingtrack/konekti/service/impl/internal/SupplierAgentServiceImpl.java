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

import com.thingtrack.konekti.domain.SupplierAgent;
import com.thingtrack.konekti.dao.api.SupplierAgentDao;
import com.thingtrack.konekti.service.api.SupplierAgentService;

/**
 * @author Thingtrack S.L.
 *
 */
public class SupplierAgentServiceImpl implements SupplierAgentService {
	@Autowired
	private SupplierAgentDao supplierAgentDao;

	@Override
	public List<SupplierAgent> getAll() throws Exception {
		return this.supplierAgentDao.getAll();
		
	}

	@Override
	public SupplierAgent get(Integer supplierAgentId) throws Exception {
		return this.supplierAgentDao.get(supplierAgentId);
		
	}

	@Override
	public SupplierAgent getByName(String name) throws Exception {
		return this.supplierAgentDao.getByName(name);
		
	}

	@Override
	public SupplierAgent save(SupplierAgent supplierAgent) throws Exception {
		return this.supplierAgentDao.save(supplierAgent);
		
	}

	@Override
	public void delete(SupplierAgent supplierAgent) throws Exception {
		this.supplierAgentDao.delete(supplierAgent);
		
	}
	
}
