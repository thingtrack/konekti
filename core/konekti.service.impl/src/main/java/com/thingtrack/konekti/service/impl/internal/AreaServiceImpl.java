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
import com.thingtrack.konekti.domain.Area;
import com.thingtrack.konekti.dao.api.AreaDao;
import com.thingtrack.konekti.service.api.SequenceService;
import com.thingtrack.konekti.service.api.AreaService;

/**
 * @author Thingtrack S.L.
 *
 */
public class AreaServiceImpl implements AreaService {
	@Autowired
	private AreaDao areaDao;

	@Autowired
	private SequenceService sequenceService;
	
	@Override
	public List<Area> getAll() throws Exception {
		return this.areaDao.getAll();
		
	}

	@Override
	public Area get(Integer areaId) throws Exception {
		return this.areaDao.get(areaId);
		
	}

	@Override
	public Area getByCode(String code) throws Exception {
		return this.areaDao.getByCode(code);
		
	}

	@Override
	public Area save(Area area) throws Exception {
		return this.areaDao.save(area);
		
	}

	@Override
	public void delete(Area area) throws Exception {
		this.areaDao.delete(area);
		
	}

	@Override
	public Area createEntity(Location location) throws Exception {
		Area area = new Area();
		
		area.setCode(sequenceService.setNextSequence(Sequence.CODE.AREA.name()));
		area.setLocation(location);	
		area.setActive(true);
		
		return area;
	}

	@Override
	public List<Area> getAllByLocation(Location location) throws Exception {
		return this.areaDao.getAllByLocation(location);
		
	}

	@Override
	public List<Area> getAreasFromOrganization(Organization organization) throws Exception {
		return this.areaDao.getAreasFromOrganization(organization);
		
	}
}
