package com.thingtrack.konekti.dao.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.MenuCommandResource;

/**
 * @author Thingtrack S.L.
 *
 */
public interface MenuCommandResourceDao extends Dao<MenuCommandResource, Integer> {
	public MenuCommandResource getById(String id, String version) throws Exception;
}
