package com.thingtrack.konekti.view.kernel.ui.layout;

import com.thingtrack.konekti.view.kernel.IModule;

public interface IViewContainer {
	public IModule getModule();
	
	public IView getSelectedView();
	public IView getNext();
	public IView getPrevious();
	
	public ISliderView getSliderView();
	
	public void addListener(IViewChangeListener listener);
	
}
