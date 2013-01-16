package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.MenuWorkbench;

/**
 * @author Thingtrack S.L.
 *
 */
public interface MenuWorkbenchService {
	public List<MenuWorkbench> getAll() throws Exception;
	public MenuWorkbench get( Integer menuWorkbenchId ) throws Exception;
}
