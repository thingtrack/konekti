package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.MenuFolderResource;

/**
 * @author Thingtrack S.L.
 *
 */
public interface MenuFolderResourceService {
	public List<MenuFolderResource> getAll() throws Exception;
	public MenuFolderResource get( Integer menuFolderResourceId ) throws Exception;
	public MenuFolderResource save(MenuFolderResource menuFolderResource) throws Exception;
	public void delete(MenuFolderResource menuFolderResource) throws Exception;
	
}
