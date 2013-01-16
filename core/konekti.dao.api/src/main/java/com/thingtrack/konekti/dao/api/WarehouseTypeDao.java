package com.thingtrack.konekti.dao.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.WarehouseType;

/**
 * @author Thingtrack S.L.
 *
 */
public interface WarehouseTypeDao extends Dao<WarehouseType, Integer> {
	public WarehouseType getByName(String name) throws Exception;
	
}
