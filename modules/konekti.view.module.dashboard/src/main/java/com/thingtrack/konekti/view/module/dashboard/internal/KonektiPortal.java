package com.thingtrack.konekti.view.module.dashboard.internal;

import org.vaadin.sasha.portallayout.PortalLayout;
import org.vaadin.sasha.portallayout.PortalLayout.PortletCloseListener;
import org.vaadin.sasha.portallayout.PortalLayout.PortletClosedEvent;
import org.vaadin.sasha.portallayout.PortalLayout.PortletCollapseEvent;
import org.vaadin.sasha.portallayout.PortalLayout.PortletCollapseListener;
import org.vaadin.sasha.portallayout.event.Context;

import com.thingtrack.konekti.view.addon.ui.AbstractPortalLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;

@SuppressWarnings("serial")
public class KonektiPortal extends Panel implements PortletCloseListener, PortletCollapseListener {
	private final GridLayout portletLayout = new GridLayout(3, 1);
	
	public KonektiPortal() {
		super();
		
		setSizeFull();
		
		portletLayout.setSizeFull();
		portletLayout.setMargin(true);
		portletLayout.setSpacing(true);
		
		setContent(portletLayout);
		
	}
	
	public void addPortlet(KonektiPortlet portlet) {
		portletLayout.addComponent(portlet);
		
	}
	 
	@Override
	public void portletCollapseStateChanged(PortletCollapseEvent event) {
		final Context context = event.getContext();
		
        getWindow().showNotification(context.getComponent().getCaption() + "collapsed " + context.getPortal().isCollapsed(context.getComponent()));
		
	}

	@Override
	public void portletClosed(PortletClosedEvent event) {
		getWindow().showNotification(event.getContext().getComponent().getCaption() + "closed");
		
	}

	//public class KonektiPortlet extends PortalLayout {	        
	public class KonektiPortlet extends AbstractPortalLayout {
		public KonektiPortlet() {
			setWidth("100%");
			setHeight("100%");
		    //setHeight("800px");
		    addCloseListener(KonektiPortal.this);
		    addCollapseListener(KonektiPortal.this);
		    setMargin(true);
		    
		}
		
		public KonektiPortal getKonektiPortal() {
			return KonektiPortal.this;
			
		}

		@Override
		protected void updateLabels() {

		}
	}
}
