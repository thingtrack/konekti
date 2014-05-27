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

import com.thingtrack.konekti.domain.ClientGroup;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.dao.api.ClientGroupDao;
import com.thingtrack.konekti.service.api.ClientGroupService;

/**
 * @author Thingtrack S.L.
 *
 */
public class ClientGroupServiceImpl implements ClientGroupService {
	@Autowired
	private ClientGroupDao clientGroupDao;

	@Override
	public List<ClientGroup> getAll(Organization organization) throws Exception {
		return this.clientGroupDao.getAll(organization);
		
	}

	@Override
	public ClientGroup get(Integer clientGroupId) throws Exception {
		return this.clientGroupDao.get(clientGroupId);
		
	}

	@Override
	public ClientGroup getByName(String name) throws Exception {
		return this.clientGroupDao.getByName(name);
		
	}

	@Override
	public ClientGroup save(ClientGroup clientGroup) throws Exception {
		return this.clientGroupDao.save(clientGroup);
		
	}

	@Override
	public void delete(ClientGroup clientGroup) throws Exception {
		this.clientGroupDao.delete(clientGroup);
		
	}

}
