package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.WarehouseType;

public interface WarehouseTypeService {
	public List<WarehouseType> getAll() throws Exception;
	public WarehouseType get( Integer warehouseTypeId ) throws Exception;
	public WarehouseType getByName( String name ) throws Exception;
	public WarehouseType save(WarehouseType warehouseType) throws Exception;
	public void delete(WarehouseType warehouseType) throws Exception;
}
