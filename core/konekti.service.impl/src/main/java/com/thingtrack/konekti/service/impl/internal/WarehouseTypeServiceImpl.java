package com.thingtrack.konekti.service.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.api.WarehouseTypeDao;
import com.thingtrack.konekti.domain.WarehouseType;
import com.thingtrack.konekti.service.api.WarehouseTypeService;

/**
 * @author Thingtrack S.L.
 *
 */
public class WarehouseTypeServiceImpl implements WarehouseTypeService {
	@Autowired
	private WarehouseTypeDao warehouseTypeDao;

	@Override
	public List<WarehouseType> getAll() throws Exception {
		return this.warehouseTypeDao.getAll();
		
	}

	@Override
	public WarehouseType get(Integer warehouseTypeId) throws Exception {
		return this.warehouseTypeDao.get(warehouseTypeId);
		
	}

	@Override
	public WarehouseType getByName(String name) throws Exception {
		return this.warehouseTypeDao.getByName(name);
		
	}

	@Override
	public WarehouseType save(WarehouseType warehouseType) throws Exception {
		return this.warehouseTypeDao.save(warehouseType);
		
	}

	@Override
	public void delete(WarehouseType warehouseType) throws Exception {
		this.warehouseTypeDao.delete(warehouseType);
		
	}
	
}
