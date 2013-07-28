package com.thingtrack.konekti.view.kernel.ui.layout;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import com.thingtrack.konekti.view.kernel.IModule;


public abstract class AbstractModule implements IModule {
	private static String symbolicName;
	private static String version;
	
	public static String getSymbolicName() {
		return symbolicName;
	}
	
	public static String getVersion() {
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
