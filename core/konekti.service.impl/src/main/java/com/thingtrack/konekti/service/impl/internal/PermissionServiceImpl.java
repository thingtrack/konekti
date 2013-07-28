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

import com.thingtrack.konekti.domain.Permission;
import com.thingtrack.konekti.domain.Role;
import com.thingtrack.konekti.dao.api.PermissionDao;
import com.thingtrack.konekti.service.api.PermissionService;

/**
 * @author Thingtrack S.L.
 *
 */
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	private PermissionDao permissionDao;

	@Override
	public List<Permission> getAll() throws Exception {
		return this.permissionDao.getAll();
		
	}

	@Override
	public Permission get(Integer permissionId) throws Exception {
		return this.permissionDao.get(permissionId);
		
	}

	@Override
	public Permission getByCode(String code) throws Exception {
		return this.permissionDao.getByCode(code);
		
	}

	@Override
	public Permission save(Permission permission) throws Exception {
		return this.permissionDao.save(permission);
		
	}

	@Override
	public void delete(Permission permission) throws Exception {
		this.permissionDao.delete(permission);
		
	}

	@Override
	public List<Permission> getAllByRole(Role role) throws Exception {
		return this.permissionDao.getAllByRole(role);
		
	}
}
