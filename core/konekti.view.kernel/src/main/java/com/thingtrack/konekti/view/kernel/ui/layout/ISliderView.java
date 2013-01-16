package com.thingtrack.konekti.view.kernel.ui.layout;

import com.thingtrack.konekti.view.kernel.ui.layout.IView;

public interface ISliderView {
	public void addView(IView view);
	
	public void rollNext();
	public void rollPrevious();
	public void rollTo(int slideTo);
	
	public IView getSelectedView();
	
	public int getViewCount();
}
