package com.thingtrack.konekti.dao.impl.internal;

import com.thingtrack.konekti.dao.api.MenuCommandResourceDao;
import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.domain.MenuCommandResource;

public class MenuCommandResourceDaoImpl extends JpaDao<MenuCommandResource, Integer> implements MenuCommandResourceDao {
	@Override
	public MenuCommandResource getById(String id, String version) throws Exception {
		MenuCommandResource menuCommandResource = (MenuCommandResource)getEntityManager()
		.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.moduleId = :moduleId AND p.moduleVersion = :moduleVersion")
		.setParameter("moduleId", id)
		.setParameter("moduleVersion", version).getSingleResult();

		return menuCommandResource;
		
	}
}
