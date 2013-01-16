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

import com.thingtrack.konekti.domain.Resource;
import com.thingtrack.konekti.dao.api.ResourceDao;
import com.thingtrack.konekti.service.api.ResourceService;

/**
 * @author Thingtrack S.L.
 *
 */
public class ResourceServiceImpl implements ResourceService {
	@Autowired
	private ResourceDao resourceDao;

	@Override
	public List<Resource> getAll() throws Exception {
		return this.resourceDao.getAll();
		
	}

	@Override
	public Resource get(Integer resourceId) throws Exception {
		return this.resourceDao.get(resourceId);
		
	}

	@Override
	public Resource getByCode(String code) throws Exception {
		return this.resourceDao.getByCode(code);
		
	}

	@Override
	public Resource save(Resource resource) throws Exception {
		return this.resourceDao.save(resource);
		
	}

	@Override
	public void delete(Resource resource) throws Exception {
		this.resourceDao.delete(resource);
		
	}

}
