package com.thingtrack.konekti.view.kernel.ui.layout;

import com.thingtrack.konekti.view.kernel.ui.layout.LOCATION;

public class ModuleEvent {
	private String id;
	private LOCATION location;
	private IViewContainer viewComponent;    	
	
	public ModuleEvent(String id, LOCATION location, IViewContainer viewComponent) {		
		this.id = id;
		this.location = location;
		this.viewComponent = viewComponent;
		
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @return the location
	 */
	public LOCATION getLocation() {
		return location;
	}


	/**
	 * @return the source
	 */
	public IViewContainer getViewComponent() {
		return viewComponent;
	}
	
}
