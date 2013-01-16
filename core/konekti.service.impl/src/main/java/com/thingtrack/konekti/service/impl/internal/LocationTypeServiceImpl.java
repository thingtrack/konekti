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

import com.thingtrack.konekti.domain.LocationType;
import com.thingtrack.konekti.dao.api.LocationTypeDao;
import com.thingtrack.konekti.service.api.LocationTypeService;

/**
 * @author Thingtrack S.L.
 *
 */
public class LocationTypeServiceImpl implements LocationTypeService {
	@Autowired
	private LocationTypeDao locationTypeDao;

	@Override
	public List<LocationType> getAll() throws Exception {
		return this.locationTypeDao.getAll();
		
	}

	@Override
	public LocationType get(Integer locationId) throws Exception {
		return this.locationTypeDao.get(locationId);
		
	}

	@Override
	public LocationType getByCode(String code) throws Exception {
		return this.locationTypeDao.getByCode(code);
		
	}

	@Override
	public LocationType save(LocationType locationType) throws Exception {
		return this.locationTypeDao.save(locationType);
		
	}

	@Override
	public void delete(LocationType locationType) throws Exception {
		this.locationTypeDao.delete(locationType);
		
	}

}
