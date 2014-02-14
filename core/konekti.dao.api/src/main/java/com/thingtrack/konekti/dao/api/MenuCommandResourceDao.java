package com.thingtrack.konekti.dao.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.MenuCommandResource;

/**
 * MenuCommand Resource Data Access Layer
 * <p>
 * @author Thingtrack S.L.
 *
 */
public interface MenuCommandResourceDao extends Dao<MenuCommandResource, Integer> {
	/**
	 * Obtains the {@link MenuCommandResource} by tis identifier and version
	 * 
	 * @param id  the unique identifier, not null 
	 * @param version the specifyied version, if not provided the lastest
	 * @return the queryied MenuCommandResource
	 * @throws Exception
	 */
	public MenuCommandResource getById(String id, String version) throws Exception;
}
