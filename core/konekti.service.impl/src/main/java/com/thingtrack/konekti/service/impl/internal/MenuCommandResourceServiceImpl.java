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

import com.thingtrack.konekti.dao.api.MenuCommandResourceDao;
import com.thingtrack.konekti.domain.MenuCommandResource;
import com.thingtrack.konekti.service.api.MenuCommandResourceService;

/**
 * @author Thingtrack S.L.
 *
 */
public class MenuCommandResourceServiceImpl implements MenuCommandResourceService {
	@Autowired
	private MenuCommandResourceDao menuCommandResourceDao;
	
	@Override
	public List<MenuCommandResource> getAll() throws Exception {
		return this.menuCommandResourceDao.getAll();
	}

	@Override
	public MenuCommandResource get(Integer menuCommandResourceId)
			throws Exception {
		return this.menuCommandResourceDao.get(menuCommandResourceId);
	}

	@Override
	public MenuCommandResource save(MenuCommandResource menuCommandResource)
			throws Exception {
		return this.menuCommandResourceDao.save(menuCommandResource);
	}

	@Override
	public void delete(MenuCommandResource menuCommandResource)
			throws Exception {
		this.menuCommandResourceDao.delete(menuCommandResource);
		
	}

	@Override
	public MenuCommandResource getById(String id, String version)
			throws Exception {
		return this.menuCommandResourceDao.getById(id, version);
		
	}

}
