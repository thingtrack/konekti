/*******************************************************************************
 * Copyright 2011 Thingtrack
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.thingtrack.konekti.view.layout;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.notifique.Notifique;
import org.vaadin.notifique.Notifique.ClickListener;
import org.vaadin.notifique.Notifique.Message;
import org.vaadin.overlay.CustomOverlay;

import com.thingtrack.konekti.domain.Configuration;
import com.thingtrack.konekti.service.api.ConfigurationService;
import com.thingtrack.konekti.view.kernel.ui.layout.IViewContainer;
import com.thingtrack.konekti.view.kernel.ui.layout.IFooterLayout;
import com.thingtrack.konekti.view.kernel.ui.layout.IHeaderLayout;
import com.thingtrack.konekti.view.kernel.ui.layout.IMenuLayout;
import com.thingtrack.konekti.view.kernel.ui.layout.IToolbarLayout;
import com.thingtrack.konekti.view.kernel.ui.layout.IWorkbenchLayout;
import com.thingtrack.konekti.view.kernel.ui.layout.LOCATION;
import com.thingtrack.konekti.view.layout.footer.FooterLayout;
import com.thingtrack.konekti.view.layout.header.HeaderLayout;
import com.thingtrack.konekti.view.layout.menu.MenuLayout;
import com.thingtrack.konekti.view.layout.toolbar.ToolbarLayout;
import com.thingtrack.konekti.view.layout.workbench.WorkbenchLayout;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class KonektiLayout extends CustomComponent {

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private FooterLayout footerLayout;

	@AutoGenerated
	private WorkbenchLayout workbenchLayout;

	@AutoGenerated
	private ToolbarLayout toolbarLayout;

	@AutoGenerated
	private MenuLayout menuLayout;

	@AutoGenerated
	private HeaderLayout headerLayout;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@Autowired
	private ConfigurationService configurationService;
	
	private String logo_workbench;
	
	//Vaadin Notifique addon to show Konekti System Events in a notification way  
	private Notifique notifications;
	
	public enum KonektiNotification{
		INFO,
		SUCCESS,
		WARNING,
		ERROR
	}
	
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
	
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public KonektiLayout() {
		this(null);
		
		/*buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		headerLayout.setStyleName("headerLayout");
		toolbarLayout.setStyleName(ToolbarLayout.TOOLBAR_LAYOUT_STYLE);
		menuLayout.setStyleName("menuLayout");
		workbenchLayout.setStyleName("workbenchLayout");
		footerLayout.setStyleName("footerLayout");
		
		//Notifications initialization
		notifications = new Notifique(true);
		notifications.setWidth("100%");
		
		notifications.setClickListener(new ClickListener() {
			
			@Override
			public void messageClicked(Message message) {
				message.hide();
			}
		});
		
		this.setImmediate(true);
		menuLayout.setImmediate(true);
		
		headerLayout.setVisible(false);*/
	}
		
	public KonektiLayout(ConfigurationService configurationService) {
		this.configurationService  = configurationService;
		
		getConfiguration();
		
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		headerLayout.setStyleName("headerLayout");
		toolbarLayout.setStyleName(ToolbarLayout.TOOLBAR_LAYOUT_STYLE);
		menuLayout.setStyleName("menuLayout");
		workbenchLayout.setStyleName("workbenchLayout");
		footerLayout.setStyleName("footerLayout");
		
		//Notifications initialization
		notifications = new Notifique(true);
		notifications.setWidth("100%");
		
		notifications.setClickListener(new ClickListener() {
			
			@Override
			public void messageClicked(Message message) {
				message.hide();
			}
		});
		
		this.setImmediate(true);
		menuLayout.setImmediate(true);
		
		headerLayout.setVisible(false);
		
	}
	
	private void getConfiguration() {
		Configuration configuration = null;
		try {
			configuration = configurationService.getByTag(Configuration.TAG.LOGO_WORKBENCH.name());
			logo_workbench = configuration.getValue();
		} catch (Exception e) {
			logo_workbench = "logo_konekti_workbench.png";
		}	
	}
	
	public IHeaderLayout getHeaderLayout() {
		return this.headerLayout;
	}
	
	public IMenuLayout getMenuLayout() {
		return this.menuLayout;
	}
	
	public IToolbarLayout getToolbarLayout() {
		return this.toolbarLayout;
	}
	
	public IWorkbenchLayout getWorkbenchLayout() {
		return this.workbenchLayout;
	}
	
	public IFooterLayout getFooterLayout() {
		return this.footerLayout;
	}
		
	public void sendNotification(KonektiNotification type, String message){		
		switch(type){
			case INFO:
				notifications.add(null, message, Notifique.Styles.INFO);
				break;			
			case SUCCESS:
				notifications.add(null, message, Notifique.Styles.SUCCESS);
				break;
			case WARNING:
				notifications.add(null, message, Notifique.Styles.WARNING);
				break;
			case ERROR:
				notifications.add(null, message, Notifique.Styles.ERROR);
				break;

		}
	}

	@Override
	public void attach() {
		super.attach();
		
		attachNotifications();
	}

	private void attachNotifications() {
		
		// Display as overlay in top of the main window
		CustomOverlay ol = new CustomOverlay(notifications, getApplication().getMainWindow());
		
		mainLayout.addComponent(ol);	
	}
	
	// Module Interface
	public void addModule(String id, String name, IViewContainer componentView, boolean closeable, Resource icon, LOCATION location) {
		workbenchLayout.addModule(id, name, componentView, closeable, icon, location);		
		
	}
	
	public void removeModule(String id) {
		workbenchLayout.removeModule(id);
		
	}
	
	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// headerLayout
		headerLayout = new HeaderLayout();
		headerLayout.setImmediate(false);
		headerLayout.setWidth("100.0%");
		headerLayout.setHeight("-1px");
		mainLayout.addComponent(headerLayout);
		
		// menuLayout
		menuLayout = new MenuLayout(logo_workbench);
		menuLayout.setImmediate(false);
		menuLayout.setWidth("100.0%");
		menuLayout.setHeight("-1px");
		mainLayout.addComponent(menuLayout);
		
		// toolbarLayout
		toolbarLayout = new ToolbarLayout();
		mainLayout.addComponent(toolbarLayout);
		
		// workbenchLayout
		workbenchLayout = new WorkbenchLayout();
		workbenchLayout.setImmediate(false);
		workbenchLayout.setWidth("100.0%");
		workbenchLayout.setHeight("100.0%");
		mainLayout.addComponent(workbenchLayout);
		mainLayout.setExpandRatio(workbenchLayout, 1.0f);
		
		// footerLayout
		footerLayout = new FooterLayout();
		footerLayout.setImmediate(false);
		footerLayout.setWidth("100.0%");
		footerLayout.setHeight("-1px");
		mainLayout.addComponent(footerLayout);
		
		return mainLayout;
	}
	
	
	public void setLoggedUser(String loggedUsername){
		
		menuLayout.setUsernameLogoutMenuItem(loggedUsername);
	} 
		
}
