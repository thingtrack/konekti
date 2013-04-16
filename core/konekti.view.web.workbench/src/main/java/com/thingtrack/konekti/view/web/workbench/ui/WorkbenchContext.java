package com.thingtrack.konekti.view.web.workbench.ui;

import com.thingtrack.konekti.domain.User;
import com.thingtrack.konekti.view.kernel.IMenuManager;
import com.thingtrack.konekti.view.kernel.IToolbarManager;
import com.thingtrack.konekti.view.kernel.IWorkbenchContext;

public class WorkbenchContext implements IWorkbenchContext {	
	private User user;
	private IMenuManager menuManager;
	private IToolbarManager toolbarManager; 
	private ResourceManager resourceManager;
	
	public WorkbenchContext(User user,
							IMenuManager menuManager,
			                IToolbarManager toolbarManager, 
			                ResourceManager resourceManager) {
		this.user = user;
		this.menuManager = menuManager;
		this.toolbarManager = toolbarManager;
		this.resourceManager = resourceManager;
	}
	
	@Override
	public User getUser(){
		return user;
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
