package com.thingtrack.konekti.view.kernel.ui.layout;

import com.vaadin.terminal.Resource;

public interface IWorkbenchPanel {
	public void setLocationLayout(LOCATION location);
	public LOCATION getLocationLayout();	
	
	public void addModule(String id, String name, IViewContainer viewComponent, boolean closeable, Resource icon, LOCATION location);
	public void removeModule(String id);
	
	public void removeModules(LOCATION location);
	
	public void addListener(IModuleCloseListener listener);
	public void addListener(IModuleChangeListener listener);
}
