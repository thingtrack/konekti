package com.thingtrack.konekti.view.module.dashboard.internal;

import com.thingtrack.konekti.view.kernel.IWorkbenchContext;
import com.thingtrack.konekti.view.module.dashboard.internal.KonektiPortal.KonektiPortlet;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class StatisticalPortlet extends KonektiPortlet {
	private Table tableTest;
	private IWorkbenchContext context;
	
	public StatisticalPortlet(IWorkbenchContext context, KonektiPortal konektiPortal) {
		konektiPortal.super();
		
		this.context = context;
		
		buildMainLayout();
	}
	
	@Override
	public void addComponent(Component c, int position) {
        super.addComponent(c, position);
        
    };
    
    private void buildMainLayout() {
		// common part: create layout
    	tableTest = new Table();
    	tableTest.setImmediate(false);
    	tableTest.setWidth("100%");
    	tableTest.setHeight("100%");
		
    	tableTest.setCaption("Portlet Statistical");
    		
		// top-level component properties
		addComponent(tableTest);
		
	}
}
