package com.thingtrack.konekti.view.addon.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.vaadin.sasha.portallayout.PortalLayout;

import com.github.peholmst.i18n4vaadin.I18N;
import com.github.peholmst.i18n4vaadin.I18NComponent;
import com.github.peholmst.i18n4vaadin.I18NListener;
import com.github.peholmst.i18n4vaadin.support.I18NComponentSupport;

import com.thingtrack.konekti.view.kernel.IWorkbenchContext;
import com.thingtrack.konekti.view.kernel.ui.layout.IPanelView;
import com.thingtrack.konekti.view.kernel.ui.layout.IToolbar;
import com.thingtrack.konekti.view.kernel.ui.layout.IView;
import com.thingtrack.konekti.view.kernel.ui.layout.IViewContainer;

@SuppressWarnings("serial")
public abstract class AbstractPortalLayout extends PortalLayout implements IView, I18NComponent, I18NListener {
	protected List<IToolbar> toolbars = new ArrayList<IToolbar>();
	protected List<IPanelView> panelviews = new ArrayList<IPanelView>();
	
	protected IViewContainer viewContainer;	
	protected IWorkbenchContext workbenchContext;
	
	private final I18NComponentSupport support = new I18NComponentSupport(this);
	
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
	
	@Override
	public void setI18N(I18N i18n) {
		support.setI18N(i18n);
	}

	@Override
	public I18N getI18N() {
		return support.getI18N();
	}
	
	@Override
	public void attach() {
		super.attach();
		getI18N().addListener(this);
		updateLabels();
	}

	@Override
	public void detach() {
		getI18N().removeListener(this);
		super.detach();
	}

	@Override
	public void localeChanged(I18N sender, Locale oldLocale, Locale newLocale)  {
		updateLabels();
	}
	
	protected abstract void updateLabels();

}
