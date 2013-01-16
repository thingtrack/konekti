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

import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.Sequence;
import com.thingtrack.konekti.dao.api.OrganizationDao;
import com.thingtrack.konekti.service.api.OrganizationService;
import com.thingtrack.konekti.service.api.SequenceService;

/**
 * @author Thingtrack S.L.
 *
 */
public class OrganizationServiceImpl implements OrganizationService {
	@Autowired
	private OrganizationDao organizationDao;

	@Autowired
	private SequenceService sequenceService;
	
	@Override
	public List<Organization> getAll() throws Exception {
		return this.organizationDao.getAll();
		
	}

	@Override
	public Organization get(Integer organizationId) throws Exception {
		return this.organizationDao.get(organizationId);
		
	}

	@Override
	public Organization getByCode(String code) throws Exception {
		return this.organizationDao.getByCode(code);
		
	}

	@Override
	public Organization save(Organization organization) throws Exception {
		return this.organizationDao.save(organization);
		
	}

	@Override
	public void delete(Organization organization) throws Exception {
		this.organizationDao.delete(organization);
		
	}

	@Override
	public Organization createEntity() throws Exception {
		Organization organization = new Organization();
		
		organization.setCode(sequenceService.setNextSequence(Sequence.CODE.ORGANIZATION.name()));
		organization.setActive(true);
		
		return organization;
	}
	
}
