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

import com.thingtrack.konekti.domain.ClientAgent;
import com.thingtrack.konekti.dao.api.ClientAgentDao;
import com.thingtrack.konekti.service.api.ClientAgentService;

/**
 * @author Thingtrack S.L.
 *
 */
public class ClientAgentServiceImpl implements ClientAgentService {
	@Autowired
	private ClientAgentDao clientAgentDao;

	@Override
	public List<ClientAgent> getAll() throws Exception {
		return this.clientAgentDao.getAll();
		
	}

	@Override
	public ClientAgent get(Integer clientAgentId) throws Exception {
		return this.clientAgentDao.get(clientAgentId);
		
	}

	@Override
	public ClientAgent getByName(String name) throws Exception {
		return this.clientAgentDao.getByName(name);
		
	}

	@Override
	public ClientAgent save(ClientAgent clientAgent) throws Exception {
		return this.clientAgentDao.save(clientAgent);
		
	}

	@Override
	public void delete(ClientAgent clientAgent) throws Exception {
		this.clientAgentDao.delete(clientAgent);
		
	}

}
