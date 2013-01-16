package com.thingtrack.konekti.view.addon.ui;

import com.thingtrack.konekti.view.addon.data.BindingSource;
import com.thingtrack.konekti.view.addon.data.BindingSourceComponent;
import com.thingtrack.konekti.view.addon.data.BindingSource.IndexChangeListener;
import com.thingtrack.konekti.view.kernel.ui.layout.IPanelView;
import com.thingtrack.konekti.view.kernel.ui.layout.LOCATION;

@SuppressWarnings("serial")
public abstract class AbstractPanelView implements IPanelView, BindingSourceComponent, IndexChangeListener {
	protected LOCATION location;
	protected BindingSource<?> bindingSource;
	
	public AbstractPanelView(LOCATION location, BindingSource<?> bindingSource) {
		this.location = location;
		this.bindingSource = bindingSource;
		
	}
		
	@Override
	public BindingSource<?> getBindingSource() {
		return this.bindingSource;
		
	}

	@Override
	public void setBindingSource(BindingSource<?> bindingSource) {
		this.bindingSource = bindingSource;
		
	}
}
