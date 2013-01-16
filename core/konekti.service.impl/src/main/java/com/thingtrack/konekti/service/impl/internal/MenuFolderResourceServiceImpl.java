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

import com.thingtrack.konekti.dao.api.MenuFolderResourceDao;
import com.thingtrack.konekti.domain.MenuFolderResource;
import com.thingtrack.konekti.service.api.MenuFolderResourceService;

/**
 * @author Thingtrack S.L.
 *
 */
public class MenuFolderResourceServiceImpl implements MenuFolderResourceService {
	@Autowired
	private MenuFolderResourceDao menuFolderResourceDao;
	
	@Override
	public List<MenuFolderResource> getAll() throws Exception {
		return this.menuFolderResourceDao.getAll();
	}

	@Override
	public MenuFolderResource get(Integer menuFolderResourceId)
			throws Exception {
		return this.menuFolderResourceDao.get(menuFolderResourceId);
	}

	@Override
	public MenuFolderResource save(MenuFolderResource menuFolderResource)
			throws Exception {
		return this.menuFolderResourceDao.save(menuFolderResource);
	}

	@Override
	public void delete(MenuFolderResource menuFolderResource) throws Exception {
		this.menuFolderResourceDao.delete(menuFolderResource);
		
	}

}
