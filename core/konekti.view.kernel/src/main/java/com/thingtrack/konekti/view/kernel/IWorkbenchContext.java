package com.thingtrack.konekti.view.kernel;

import java.util.Locale;

import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.User;
import com.thingtrack.konekti.domain.Area;

public interface IWorkbenchContext {
	public Organization getOrganization();
	public Area getArea();
	public User getUser();
	public Locale getLocale();
	public IToolbarManager getToolbarManager();
	public void openBundle(String bundleSymbolicName, String bundleVersion) throws Exception; 
	
}
