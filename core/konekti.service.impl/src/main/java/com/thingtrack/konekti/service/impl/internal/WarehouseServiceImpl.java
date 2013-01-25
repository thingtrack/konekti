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
import com.thingtrack.konekti.domain.Warehouse;
import com.thingtrack.konekti.dao.api.WarehouseDao;
import com.thingtrack.konekti.service.api.SequenceService;
import com.thingtrack.konekti.service.api.WarehouseService;

/**
 * @author Thingtrack S.L.
 *
 */
public class WarehouseServiceImpl implements WarehouseService {
	@Autowired
	private WarehouseDao warehouseDao;

	@Autowired
	private SequenceService sequenceService;
	
	@Override
	public List<Warehouse> getAll() throws Exception {
		return this.warehouseDao.getAll();
		
	}

	@Override
	public Warehouse get(Integer warehouseId) throws Exception {
		return this.warehouseDao.get(warehouseId);
		
	}

	@Override
	public Warehouse getByCode(String code) throws Exception {
		return this.warehouseDao.getByCode(code);
		
	}

	@Override
	public Warehouse save(Warehouse warehouse) throws Exception {
		return this.warehouseDao.save(warehouse);
		
	}

	@Override
	public void delete(Warehouse warehouse) throws Exception {
		this.warehouseDao.delete(warehouse);
		
	}

	@Override
	public Warehouse createEntity(Location location) throws Exception {
		Warehouse warehouse = new Warehouse();
		
		warehouse.setCode(sequenceService.setNextSequence(Sequence.CODE.WAREHOUSE.name()));
		warehouse.setLocation(location);	
		warehouse.setActive(true);
		
		return warehouse;
	}

	@Override
	public List<Warehouse> getWarehousesFromOrganization(Organization organization) throws Exception {
		return this.warehouseDao.getWarehousesFromOrganization(organization);
		
	}
}
