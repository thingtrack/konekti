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

import com.thingtrack.konekti.domain.Location;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.Sequence;
import com.thingtrack.konekti.dao.api.LocationDao;
import com.thingtrack.konekti.service.api.LocationService;
import com.thingtrack.konekti.service.api.SequenceService;

/**
 * @author Thingtrack S.L.
 *
 */
public class LocationServiceImpl  implements LocationService {
	@Autowired
	private LocationDao locationDao;

	@Autowired
	private SequenceService sequenceService;
	
	@Override
	public List<Location> getAll() throws Exception {
		return this.locationDao.getAll();
		
	}

	@Override
	public Location get(Integer locationId) throws Exception {
		return this.locationDao.get(locationId);
		
	}

	@Override
	public Location getByName(String name) throws Exception {
		return this.locationDao.getByName(name);
		
	}

	@Override
	public Location save(Location location) throws Exception {
		return this.locationDao.save(location);
		
	}

	@Override
	public void delete(Location location) throws Exception {
		this.locationDao.delete(location);
		
	}

	@Override
	public Location createEntity(Organization organization) throws Exception {
		Location location = new Location();
		
		location.setCode(sequenceService.setNextSequence(Sequence.CODE.LOCATION.name()));
		location.addOrganization(organization);	
		location.setActive(true);
		
		return location;
	}	
	
}
