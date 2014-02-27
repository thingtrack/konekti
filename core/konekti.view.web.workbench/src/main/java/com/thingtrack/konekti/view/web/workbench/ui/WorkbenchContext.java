package com.thingtrack.konekti.view.web.workbench.ui;

import java.util.List;

import com.thingtrack.konekti.domain.Configuration;
import com.thingtrack.konekti.domain.User;
import com.thingtrack.konekti.view.kernel.IMenuManager;
import com.thingtrack.konekti.view.kernel.IToolbarManager;
import com.thingtrack.konekti.view.kernel.IWorkbenchContext;

public class WorkbenchContext implements IWorkbenchContext {	
	private User user;
	private List<Configuration> configurations;
	private IMenuManager menuManager;
	private IToolbarManager toolbarManager; 
	private ResourceManager resourceManager;
	
	public WorkbenchContext(User user,
						    List<Configuration> configurations,
							IMenuManager menuManager,
			                IToolbarManager toolbarManager, 
			                ResourceManager resourceManager) {
		this.user = user;
		this.configurations = configurations;
		this.menuManager = menuManager;
		this.toolbarManager = toolbarManager;
		this.resourceManager = resourceManager;
	}
	
	@Override
	public User getUser(){
		return user;
		
	}
	
	@Override
	public Configuration getConfiguration(String tag) throws Exception {
		for(Configuration configuration : configurations) {
			if  (configuration.getTag().equals(tag))
				return configuration;
		}
		
		throw new Exception("Not exist any configuration for this " + tag  + "!");
	}
	
	@Override
	public IMenuManager getMenuManager() {
		return menuManager;
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
