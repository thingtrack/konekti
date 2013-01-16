package com.thingtrack.konekti.view.addon.ui;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.CustomComponent;

import com.thingtrack.konekti.view.kernel.IWorkbenchContext;
import com.thingtrack.konekti.view.kernel.ui.layout.IPanelView;
import com.thingtrack.konekti.view.kernel.ui.layout.IToolbar;
import com.thingtrack.konekti.view.kernel.ui.layout.IView;
import com.thingtrack.konekti.view.kernel.ui.layout.IViewContainer;

@SuppressWarnings("serial")
public abstract class AbstractView extends CustomComponent implements IView {
	protected List<IToolbar> toolbars = new ArrayList<IToolbar>();
	protected List<IPanelView> panelviews = new ArrayList<IPanelView>();
	
	protected IViewContainer viewContainer;	
	protected IWorkbenchContext workbenchContext;
	
	// inject slider layout component
	public void setViewComponent(IViewContainer viewContainer) {
		this.viewContainer = viewContainer;
				
	}
	
	public void setViewComponent(IViewContainer viewContainer, IWorkbenchContext workbenchContext) {
		this.viewContainer = viewContainer;
	    this.workbenchContext = workbenchContext;
	    
	}
	
	// Toolbar View management
	@Override
	public List<IToolbar> getToolbars() {	
		return toolbars;
		
	}

	@Override
	public void addToolbar(IToolbar toolbar) {
		toolbars.add(toolbar);		
		
	}
	
	@Override
	public void removeToolbar(IToolbar toolbar) {
		toolbars.remove(toolbar);
		
	}
	
	@Override
	public void removeAllToolbar() {
		toolbars.clear();
		
	}
	
	// PanelView View management
	@Override
	public List<IPanelView> getPanelView() {
		return panelviews;
		
	}

	@Override
	public void addPanelView(IPanelView panelView) {
		panelviews.add(panelView);
		
	}

	@Override
	public void removePanelView(IPanelView panelView) {
		panelviews.remove(panelView);
		
	}
	
	@Override
	public void removeAllPanelView() {
		panelviews.clear();
		
	}
}
