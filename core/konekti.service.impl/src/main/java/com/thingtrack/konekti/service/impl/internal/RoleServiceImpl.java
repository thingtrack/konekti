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

import com.thingtrack.konekti.domain.Area;
import com.thingtrack.konekti.domain.Role;
import com.thingtrack.konekti.dao.api.RoleDao;
import com.thingtrack.konekti.service.api.RoleService;

/**
 * @author Thingtrack S.L.
 *
 */
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;

	@Override
	public List<Role> getAll() throws Exception {
		return this.roleDao.getAll();
		
	}

	@Override
	public Role get(Integer resourceId) throws Exception {
		return this.roleDao.get(resourceId);
		
	}

	@Override
	public Role getByCode(String code) throws Exception {
		return this.roleDao.getByCode(code);
		
	}

	@Override
	public Role save(Role role) throws Exception {
		return this.roleDao.save(role);
		
	}

	@Override
	public void delete(Role role) throws Exception {
		this.roleDao.delete(role);
		
	}

	@Override
	public List<Role> getAll(Area area) throws Exception {
		return this.roleDao.getAll(area);
		
	}
}
