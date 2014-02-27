package com.thingtrack.konekti.view.kernel;

import com.thingtrack.konekti.domain.Configuration;
import com.thingtrack.konekti.domain.User;

public interface IWorkbenchContext {
	public User getUser();
	public Configuration getConfiguration(String tag) throws Exception;
	public IMenuManager getMenuManager();
	public IToolbarManager getToolbarManager();
	public void openBundle(String bundleSymbolicName, String bundleVersion) throws Exception; 
	
}
