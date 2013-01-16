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

import com.thingtrack.konekti.domain.ClientType;
import com.thingtrack.konekti.dao.api.ClientTypeDao;
import com.thingtrack.konekti.service.api.ClientTypeService;

/**
 * @author Thingtrack S.L.
 *
 */
public class ClientTypeServiceImpl implements ClientTypeService {
	@Autowired
	private ClientTypeDao clientTypeDao;

	@Override
	public List<ClientType> getAll() throws Exception {
		return this.clientTypeDao.getAll();
		
	}

	@Override
	public ClientType get(Integer clientGroupId) throws Exception {
		return this.clientTypeDao.get(clientGroupId);
		
	}

	@Override
	public ClientType getByCode(String code) throws Exception {
		return this.clientTypeDao.getByCode(code);
		
	}

	@Override
	public ClientType save(ClientType clientType) throws Exception {
		return this.clientTypeDao.save(clientType);
		
	}

	@Override
	public void delete(ClientType clientType) throws Exception {
		this.clientTypeDao.delete(clientType);
		
	}

}
