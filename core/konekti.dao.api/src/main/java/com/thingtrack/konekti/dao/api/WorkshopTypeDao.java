package com.thingtrack.konekti.dao.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.WorkshopType;

/**
 * @author Thingtrack S.L.
 *
 */
public interface WorkshopTypeDao extends Dao<WorkshopType, Integer> {
	public WorkshopType getByCode(String code) throws Exception;

}
