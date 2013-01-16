package com.thingtrack.konekti.view.layout.workbench;

import java.util.Hashtable;
import java.util.Map.Entry;

import com.thingtrack.konekti.view.kernel.ui.layout.IViewContainer;
import com.thingtrack.konekti.view.kernel.ui.layout.IModuleChangeListener;
import com.thingtrack.konekti.view.kernel.ui.layout.IModuleCloseListener;
import com.thingtrack.konekti.view.kernel.ui.layout.IWorkbenchPanel;
import com.thingtrack.konekti.view.kernel.ui.layout.LOCATION;
import com.thingtrack.konekti.view.kernel.ui.layout.ModuleEvent;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
public class WorkbenchPanel extends CustomComponent implements IWorkbenchPanel {

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private Panel panel;
	@AutoGenerated
	private VerticalLayout verticalLayoutPanel;
	@AutoGenerated
	private TabSheet tabSheetModule;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private IModuleCloseListener moduleCloselistener = null;
	private IModuleChangeListener moduleChangelistener = null;
	
	private LOCATION locationLayout;
	
	private Hashtable<String, IViewContainer> modules;
	
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public WorkbenchPanel() {
		this(LOCATION.CENTER);
				
	}

	public WorkbenchPanel(LOCATION locationLayout) {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		this.locationLayout = locationLayout;
		
		// default hide tabs
		tabSheetModule.hideTabs(true);
		
		// add close tab handler
		tabSheetModule.setCloseHandler(new TabSheet.CloseHandler() {			
			@Override
			public void onTabClose(TabSheet tabsheet, Component tabContent) {										
				tabsheet.removeComponent(tabContent);
				
				if (tabsheet.getComponentCount() > 0)
					tabsheet.hideTabs(false);
				else
					tabsheet.hideTabs(true);
				
				String moduleIdSelected = null;
				LOCATION moduleLocationSelected = getLocationLayout();
				Component moduleComponentSelected = null;
				
				if (tabsheet.getSelectedTab() != null) {
					Tab tabSelected = tabsheet.getTab(tabsheet.getSelectedTab());
					
					moduleIdSelected = getKeyByValue((IViewContainer) tabSelected.getComponent());
					moduleComponentSelected = tabsheet.getSelectedTab();						
				}				
					
				if (moduleCloselistener != null)
					moduleCloselistener.moduleClose(new ModuleEvent(moduleIdSelected, moduleLocationSelected, (IViewContainer) moduleComponentSelected));
				
			}
		});
		
		// add selected change tab handler
		tabSheetModule.addListener(new SelectedTabChangeListener() {			
			@Override
			public void selectedTabChange(SelectedTabChangeEvent event) {
				Tab tabSelected = event.getTabSheet().getTab(event.getTabSheet().getSelectedTab());
				
				String moduleIdSelected = getKeyByValue((IViewContainer) tabSelected.getComponent());
				LOCATION moduleLocationSelected = getLocationLayout();
				Component moduleComponentSelected = event.getTabSheet().getSelectedTab();
				
				if (moduleChangelistener != null)
					moduleChangelistener.moduleChange(new ModuleEvent(moduleIdSelected, moduleLocationSelected, (IViewContainer) moduleComponentSelected));
											
			}
		});
		
		// default module list
		modules = new Hashtable<String, IViewContainer>();
	}
	
	@Override
	public void setLocationLayout(LOCATION locationLayout) {
		this.locationLayout = locationLayout;
		
	}

	@Override
	public LOCATION getLocationLayout() {
		return this.locationLayout;
		
	}

	@Override
	public void addModule(String id, String name, IViewContainer viewComponent, Resource icon, LOCATION location) {
		if (tabSheetModule.getComponentCount() == 0) 
			tabSheetModule.hideTabs(false);

		// add module to the list
		modules.put(id, viewComponent);
		
		// create new tab for the current module
		Tab tab = tabSheetModule.addTab((Component) viewComponent, name, icon);			
		tab.setClosable(true); // default closable tabs
				
		tabSheetModule.setSelectedTab((Component) viewComponent);
				
	}

	@Override
	public void removeModule(String id) {
		if (modules.get(id) == null)
			return;
		
		// remove tab from tab sheet
		tabSheetModule.removeComponent((Component) modules.get(id));
		
		if (tabSheetModule.getComponentCount() == 0) 
			tabSheetModule.hideTabs(true);
		
		// remove module from the list
		modules.remove(id);
		
	}
	
	@Override
	public void addListener(IModuleCloseListener listener) {
		this.moduleCloselistener = listener;
		
	}

	@Override
	public void addListener(IModuleChangeListener listener) {
		this.moduleChangelistener = listener;
		
	}
	
	private String getKeyByValue(IViewContainer value) {		
	    for (Entry<String, IViewContainer> entry : modules.entrySet()) {
	        if (value == entry.getValue()) {
	            return entry.getKey();
	            
	        }
	    }
	    
	    return null;
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
		
		// panel
		panel = buildPanel();
		mainLayout.addComponent(panel);
		mainLayout.setExpandRatio(panel, 1.0f);
		
		return mainLayout;
	}
	
	@AutoGenerated
	private Panel buildPanel() {
		// common part: create layout
		panel = new Panel();
		panel.setImmediate(false);
		panel.setWidth("100.0%");
		panel.setHeight("100.0%");
		panel.setStyleName(Reindeer.PANEL_LIGHT);
		
		// verticalLayoutPanel
		verticalLayoutPanel = buildVerticalLayoutPanel();
		panel.setContent(verticalLayoutPanel);
		
		return panel;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayoutPanel() {
		// common part: create layout
		verticalLayoutPanel = new VerticalLayout();
		verticalLayoutPanel.setImmediate(false);
		verticalLayoutPanel.setWidth("100.0%");
		verticalLayoutPanel.setHeight("100.0%");
		verticalLayoutPanel.setMargin(false);
		
		// tabSheetModule
		tabSheetModule = new TabSheet();
		tabSheetModule.setImmediate(false);
		tabSheetModule.setWidth("100.0%");
		tabSheetModule.setHeight("100.0%");

		verticalLayoutPanel.addComponent(tabSheetModule);
		verticalLayoutPanel.setExpandRatio(tabSheetModule, 1.0f);
		
		return verticalLayoutPanel;
	}

}
