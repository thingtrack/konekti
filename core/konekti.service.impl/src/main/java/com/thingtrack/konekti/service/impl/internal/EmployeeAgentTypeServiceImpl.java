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

import com.thingtrack.konekti.domain.EmployeeAgentType;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.dao.api.EmployeeAgentTypeDao;
import com.thingtrack.konekti.service.api.EmployeeAgentTypeService;

/**
 * @author Thingtrack S.L.
 *
 */
public class EmployeeAgentTypeServiceImpl implements EmployeeAgentTypeService {
	@Autowired
	private EmployeeAgentTypeDao employeeAgentTypeDao;

	@Override
	public List<EmployeeAgentType> getAll(Organization organization) throws Exception {
		return this.employeeAgentTypeDao.getAll(organization);
		
	}

	@Override
	public EmployeeAgentType get(Integer clientGroupId) throws Exception {
		return this.employeeAgentTypeDao.get(clientGroupId);
		
	}

	@Override
	public EmployeeAgentType getByName(String name) throws Exception {
		return this.employeeAgentTypeDao.getByName(name);
		
	}

	@Override
	public EmployeeAgentType save(EmployeeAgentType employeeAgentType) throws Exception {
		return this.employeeAgentTypeDao.save(employeeAgentType);
		
	}

	@Override
	public void delete(EmployeeAgentType employeeAgentType) throws Exception {
		this.employeeAgentTypeDao.delete(employeeAgentType);
		
	}

}
