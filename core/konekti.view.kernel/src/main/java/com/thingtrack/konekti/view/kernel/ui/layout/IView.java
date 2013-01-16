package com.thingtrack.konekti.view.kernel.ui.layout;

import java.util.List;


public interface IView {
	//public int getLevel();
	
	public List<IToolbar> getToolbars();
	public void addToolbar(IToolbar toolbar);	
	public void removeToolbar(IToolbar toolbar);
	public void removeAllToolbar();
	
	public List<IPanelView> getPanelView();
	public void addPanelView(IPanelView panelView);	
	public void removePanelView(IPanelView panelView);
	public void removeAllPanelView();
}
