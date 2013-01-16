package com.thingtrack.konekti.view.web.workbench.ui;

import java.util.Hashtable;

import com.thingtrack.konekti.view.kernel.ui.layout.IViewContainer;
import com.thingtrack.konekti.view.kernel.ui.layout.LOCATION;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.TabSheet.Tab;

public class ResourceManager {
	private Hashtable<String, Resource> resources = new Hashtable<String, ResourceManager.Resource>();

	public class Resource {
		private String id; 											 // bundle unique identification; [bundle symbolic name]#[bundle Version]
		private String caption; 									 // bundle friendly name
		private com.vaadin.terminal.Resource resource;				 // bundle icon	
		private LOCATION location;   								 // bundle location layout
		private boolean autoStart;   								 // bundle autostart flag
		
		private IViewContainer componentView; 						 // bundle payload: visual component resource	
		private MenuItem menu; 										 // bundle payload: menu item resource
		private Tab tab; 											 // bundle payload: tab resource 
		
		public Resource(String id, String caption, com.vaadin.terminal.Resource resource, LOCATION location, boolean autoStart, IViewContainer componentView, MenuItem menu, Tab tab) {
			this.id = id;
			this.caption = caption;
			this.resource = resource;
			this.location = location;			
			this.autoStart = autoStart;
			this.componentView = componentView;
			this.menu = menu;
			this.tab = tab;
			
		}

		/**
		 * @return the component
		 */
		public IViewContainer getComponentView() {
			return componentView;
		}

		/**
		 * @param component the component to set
		 */
		public void setComponentView(IViewContainer componentView) {
			this.componentView = componentView;
		}

		/**
		 * @return the menu
		 */
		public MenuItem getMenu() {
			return menu;
		}

		/**
		 * @param menu the menu to set
		 */
		public void setMenu(MenuItem menu) {
			this.menu = menu;
		}

		/**
		 * @return the tab
		 */
		public Tab getTab() {
			return tab;
		}

		/**
		 * @param tab the tab to set
		 */
		public void setTab(Tab tab) {
			this.tab = tab;
		}

		/**
		 * @param location the location to set
		 */
		public void setLocation(LOCATION location) {
			this.location = location;
		}

		/**
		 * @return the location
		 */
		public LOCATION getLocation() {
			return location;
		}

		/**
		 * @param autoStart the autoStart to set
		 */
		public void setAutoStart(boolean autoStart) {
			this.autoStart = autoStart;
		}

		/**
		 * @return the autoStart
		 */
		public boolean isAutoStart() {
			return autoStart;
		}

		/**
		 * @return the id
		 */
		public String getId() {
			return id;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(String id) {
			this.id = id;
		}

		/**
		 * @return the name
		 */
		public String getCaption() {
			return caption;
		}

		/**
		 * @param name the name to set
		 */
		public void setCaption(String caption) {
			this.caption = caption;
		}

		/**
		 * @param resource the resource to set
		 */
		public void setResource(com.vaadin.terminal.Resource resource) {
			this.resource = resource;
		}

		/**
		 * @return the resource
		 */
		public com.vaadin.terminal.Resource getResource() {
			return resource;
		}
		
	}
	
	public Resource getResource(String id) {
		return resources.get(id);
		
	}
	
	public void addResource(String id, String caption, com.vaadin.terminal.Resource resource, LOCATION location, boolean autoStart, IViewContainer componentView, MenuItem menu, Tab tab) {
		resources.put(id, new Resource(id, caption, resource, location, autoStart, componentView, menu, tab));
		
	}
	
	public void removeResource(String id) {
		resources.remove(id);			
		
	}
		
}
