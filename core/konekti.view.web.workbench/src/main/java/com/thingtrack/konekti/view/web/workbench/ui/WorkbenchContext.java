package com.thingtrack.konekti.view.web.workbench.ui;

import java.util.Locale;

import com.thingtrack.konekti.domain.Location;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.User;
import com.thingtrack.konekti.domain.Area;
import com.thingtrack.konekti.view.kernel.IToolbarManager;
import com.thingtrack.konekti.view.kernel.IWorkbenchContext;

public class WorkbenchContext implements IWorkbenchContext {
	
	private Organization organization;
	private Location location;
	private Area area;
	
	private User user;
	private Locale locale;
	private IToolbarManager toolbarManager; 
	private ResourceManager resourceManager;
	
	public WorkbenchContext(Organization organization,
							Location location,
			                Area area,
			                User user, 
			                Locale locale, 
			                IToolbarManager toolbarManager, 
			                ResourceManager resourceManager) {
		
		this.organization = organization;
		this.location = location;
		this.area = area;
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
	public Area getArea() {
		return area;
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
