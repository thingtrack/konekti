package com.thingtrack.konekti.dao.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.Workshop;

/**
 * @author Thingtrack S.L.
 *
 */
public interface WorkshopDao extends Dao<Workshop, Integer> {
	public Workshop getByCode(String code) throws Exception;

}
