package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.MenuCommandResource;

public interface MenuCommandResourceService {
	public List<MenuCommandResource> getAll() throws Exception;
	public MenuCommandResource get( Integer menuCommandResourceId ) throws Exception;
	public MenuCommandResource save(MenuCommandResource menuCommandResource) throws Exception;
	public void delete(MenuCommandResource menuCommandResource) throws Exception;
	
	public MenuCommandResource getById(String id, String version) throws Exception;
}
