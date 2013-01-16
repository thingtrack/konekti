package com.thingtrack.konekti.view.kernel;


public class MetadataModule {
	
	private String id;
	private String version;
	private IModule module;
	
	/**
	 * 
	 * @param id: bundle symbolic name
	 * @param version: bundle version number
	 * @param module: bundle interfaz component
	 */
	public MetadataModule(String id, String version, IModule module) {
		this.id = id;
		this.version = version;
		this.module = module;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	
	/**
	 * @return the module
	 */
	public IModule getModule() {
		return module;
	}
	
}
