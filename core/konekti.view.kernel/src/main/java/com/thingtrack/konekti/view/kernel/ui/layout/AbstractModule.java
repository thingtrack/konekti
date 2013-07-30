package com.thingtrack.konekti.view.kernel.ui.layout;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import com.thingtrack.konekti.view.kernel.IModule;

public abstract class AbstractModule implements IModule {
	private String symbolicName;
	private String version;
	
	public String getSymbolicName() {
		return symbolicName;
	}
	
	public String getVersion() {
		return version;
	}
		
	protected void getBundleIdentity(Class<?> bundleClass) {
		try {
			BundleContext bundleContext = FrameworkUtil.getBundle(bundleClass).getBundleContext();
			
			symbolicName = bundleContext.getBundle().getSymbolicName();
			version = bundleContext.getBundle().getVersion().toString();
		}
		catch (Exception e) {
			e.getMessage();
			
		}
	}
}
