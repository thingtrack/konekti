package com.thingtrack.konekti.view.web.workbench.ui;

import java.util.Locale;

import com.thingtrack.konekti.domain.Location;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.User;
import com.thingtrack.konekti.domain.Warehouse;
import com.thingtrack.konekti.domain.Workshop;
import com.thingtrack.konekti.view.kernel.IToolbarManager;
import com.thingtrack.konekti.view.kernel.IWorkbenchContext;

public class WorkbenchContext implements IWorkbenchContext {
	
	private Organization organization;
	private Location location;
	private Warehouse warehouse;
	private Workshop workshop;
	
	private User user;
	private Locale locale;
	private IToolbarManager toolbarManager; 
	private ResourceManager resourceManager;
	
	public WorkbenchContext(Organization organization,
							Location location,
			                Warehouse warehouse,
			                Workshop workshop,
			                User user, 
			                Locale locale, 
			                IToolbarManager toolbarManager, 
			                ResourceManager resourceManager) {
		
		this.organization = organization;
		this.location = location;
		this.warehouse = warehouse;
		this.workshop = workshop;
		this.user = user;
		this.locale = locale;
		this.toolbarManager = toolbarManager;
		this.resourceManager = resourceManager;
	}
	
	@Override
	public Organization getOrganization(){
		return organization;
	}
	
	@Override
	public Warehouse getWarehouse() {
		return warehouse;
	}
	
	@Override
	public User getUser(){
		return user;
	}

	@Override
	public Locale getLocale() {
		return locale;
	}
	
	@Override
	public IToolbarManager getToolbarManager() {
		return toolbarManager;
	}

	@Override
	public void openBundle(String bundleSymbolicName, String bundleVersion) throws Exception {
		com.thingtrack.konekti.view.web.workbench.ui.ResourceManager.Resource resource = resourceManager.getResource(bundleSymbolicName + "#" + bundleVersion);
		
		if (resource == null)
			throw new Exception("¡No se pudo abrir el bundle con nombre: " + bundleSymbolicName + "; versión: " + bundleVersion + "!");
		
		resource.getMenu().getCommand().menuSelected(null);
			
			
	}

}
