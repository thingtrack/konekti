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

import com.thingtrack.konekti.domain.EmployeeAgentStatus;
import com.thingtrack.konekti.dao.api.EmployeeAgentStatusDao;
import com.thingtrack.konekti.service.api.EmployeeAgentStatusService;

/**
 * @author Thingtrack S.L.
 *
 */
public class EmployeeAgentStatusServiceImpl implements EmployeeAgentStatusService {
	@Autowired
	private EmployeeAgentStatusDao employeeAgentStatusDao;

	@Override
	public List<EmployeeAgentStatus> getAll() throws Exception {
		return this.employeeAgentStatusDao.getAll();
		
	}

	@Override
	public EmployeeAgentStatus get(Integer clientGroupId) throws Exception {
		return this.employeeAgentStatusDao.get(clientGroupId);
		
	}

	@Override
	public EmployeeAgentStatus getByName(String name) throws Exception {
		return this.employeeAgentStatusDao.getByName(name);
		
	}

	@Override
	public EmployeeAgentStatus save(EmployeeAgentStatus employeeAgentStatus) throws Exception {
		return this.employeeAgentStatusDao.save(employeeAgentStatus);
		
	}

	@Override
	public void delete(EmployeeAgentStatus employeeAgentStatus) throws Exception {
		this.employeeAgentStatusDao.delete(employeeAgentStatus);
		
	}

}
