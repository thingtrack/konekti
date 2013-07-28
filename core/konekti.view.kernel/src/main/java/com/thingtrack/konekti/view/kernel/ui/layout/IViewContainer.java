package com.thingtrack.konekti.view.kernel.ui.layout;

public interface IViewContainer {	
	public IView getSelectedView();
	public IView getNext();
	public IView getPrevious();
	
	public ISliderView getSliderView();
	
	public void addListener(IViewChangeListener listener);
	
}
