package com.thingtrack.konekti.view.addon.ui;

import com.thingtrack.konekti.view.addon.data.BindingSource;
import com.thingtrack.konekti.view.addon.data.BindingSource.IndexChangeListener;
import com.thingtrack.konekti.view.addon.data.BindingSourceComponent;
import com.thingtrack.konekti.view.kernel.ui.layout.IToolbar;
import com.vaadin.ui.CssLayout;

@SuppressWarnings("serial")
public abstract class AbstractToolbar extends CssLayout implements IToolbar, BindingSourceComponent, IndexChangeListener {
	protected int position;
	protected BindingSource<?> bindingSource;
	
	public AbstractToolbar(int position, BindingSource<?> bindingSource) {
		this.position = position;
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
