package com.thingtrack.konekti.dao.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.AreaType;

/**
 * @author Thingtrack S.L.
 *
 */
public interface AreaTypeDao extends Dao<AreaType, Integer> {
	public AreaType getByName(String name) throws Exception;
	
}
