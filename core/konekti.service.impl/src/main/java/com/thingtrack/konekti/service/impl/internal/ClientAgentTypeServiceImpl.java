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

import com.thingtrack.konekti.domain.ClientAgentType;
import com.thingtrack.konekti.dao.api.ClientAgentTypeDao;
import com.thingtrack.konekti.service.api.ClientAgentTypeService;

/**
 * @author Thingtrack S.L.
 *
 */
public class ClientAgentTypeServiceImpl implements ClientAgentTypeService {
	@Autowired
	private ClientAgentTypeDao clientAgentTypeDao;

	@Override
	public List<ClientAgentType> getAll() throws Exception {
		return this.clientAgentTypeDao.getAll();
		
	}

	@Override
	public ClientAgentType get(Integer clientAgentTypeId) throws Exception {
		return this.clientAgentTypeDao.get(clientAgentTypeId);
		
	}

	@Override
	public ClientAgentType getByCode(String code) throws Exception {
		return this.clientAgentTypeDao.getByCode(code);
		
	}

	@Override
	public ClientAgentType save(ClientAgentType clientAgentType) throws Exception {
		return this.clientAgentTypeDao.save(clientAgentType);
		
	}

	@Override
	public void delete(ClientAgentType clientAgentType) throws Exception {
		this.clientAgentTypeDao.delete(clientAgentType);
		
	}

}
