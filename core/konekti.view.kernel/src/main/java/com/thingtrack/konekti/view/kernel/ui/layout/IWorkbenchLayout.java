package com.thingtrack.konekti.view.kernel.ui.layout;

import com.vaadin.terminal.Resource;

public interface IWorkbenchLayout {
	public IWorkbenchPanel getTopPanelWorkbench();
	public IWorkbenchPanel getLeftPanelWorkbench();
	public IWorkbenchPanel getCentralPanelWorkbench();	
	public IWorkbenchPanel getRightPanelWorkbench();
	public IWorkbenchPanel getBottonPanelWorkbench();
	
	public void setTopPanelWorkbenchVisible(boolean visible);
	public boolean isTopPanelWorkbenchVisible();
	public void setLeftPanelWorkbenchVisible(boolean visible);
	public boolean isLeftPanelWorkbenchVisible();
	public void setRightPanelWorkbenchVisible(boolean visible);
	public boolean isRightPanelWorkbenchVisible();
	public void setBottonPanelWorkbenchVisible(boolean visible);
	public boolean isBottonPanelWorkbenchVisible();
	
	public void addModule(String id, String name, IViewContainer componentView, boolean closeable, Resource icon, LOCATION location);
	public void removeModule(String id);
	public void removeModules(LOCATION location);
	
}
