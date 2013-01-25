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

import com.thingtrack.konekti.domain.Client;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.Sequence;
import com.thingtrack.konekti.domain.User;
import com.thingtrack.konekti.dao.api.ClientDao;
import com.thingtrack.konekti.service.api.ClientService;
import com.thingtrack.konekti.service.api.SequenceService;

/**
 * @author Thingtrack S.L.
 *
 */
public class ClientServiceImpl implements ClientService {
	@Autowired
	private ClientDao clientDao;

	@Autowired
	private SequenceService sequenceService;
	
	@Override
	public List<Client> getAll() throws Exception {
		return this.clientDao.getAll();
	}

	@Override
	public Client get(Integer clientGroupId) throws Exception {
		return this.clientDao.get(clientGroupId);
		
	}

	@Override
	public Client getByCode(String code) throws Exception {
		return this.clientDao.getByCode(code);
		
	}

	@Override
	public List<Client> getByCode(Organization organization, String code) throws Exception {
		return this.clientDao.getByCode(organization, code);
		
	}
	
	@Override
	public Client save(Client client) throws Exception {
		return this.clientDao.save(client);
		
	}

	@Override
	public void delete(Client client) throws Exception {
		this.clientDao.delete(client);		
		
	}

	@Override
	public Client createNewClient(Organization organization) throws Exception {
		Client client = new Client();
		
		client.setCode(sequenceService.setNextSequence(Sequence.CODE.CLIENT.name()));
		client.setActive(true);
		
		return client;
	}
	
	@Override
	public Client getByUser(User user) throws Exception {
		return this.clientDao.getByUser(user);
		
	}
}
