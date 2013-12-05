package com.thingtrack.konekti.view.web.workbench;

import java.util.List;

import com.thingtrack.konekti.domain.User;
import com.thingtrack.konekti.view.addon.ui.AbstractView;
import com.thingtrack.konekti.view.addon.ui.SliderView;
import com.thingtrack.konekti.view.kernel.ui.layout.IPanelView;
import com.thingtrack.konekti.view.kernel.ui.layout.IToolbar;
import com.thingtrack.konekti.view.layout.KonektiLayout;

public class WorkbenchView extends AbstractView {

	private SliderView sliderView;
	
	private User loggedUser;
	
	private KonektiLayout konektiLayout;
	
	
	public User getLoggedUser() {
		return loggedUser;
	}


	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
//		konektiLayout.getHeaderLayout().setLogon(loggedUser.getUsername());
	}

	public WorkbenchView(KonektiLayout konektiLayout, SliderView sliderView) {
	
		this.konektiLayout = konektiLayout;
		this.sliderView = sliderView;
		
		// configure Workbench Layout and Panel Workbench listener
		konektiLayout.getWorkbenchLayout().setTopPanelWorkbenchVisible(false);
		konektiLayout.getWorkbenchLayout().setLeftPanelWorkbenchVisible(false);
		konektiLayout.getWorkbenchLayout().setRightPanelWorkbenchVisible(false);
		konektiLayout.getWorkbenchLayout().setBottonPanelWorkbenchVisible(false);
		
		setCompositionRoot(konektiLayout);
	}
	
	
	@Override
	public void addPanelView(IPanelView arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addToolbar(IToolbar arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<IPanelView> getPanelView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IToolbar> getToolbars() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeAllPanelView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeAllToolbar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePanelView(IPanelView arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeToolbar(IToolbar arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	protected void updateLabels() {
		// TODO Auto-generated method stub
		
	}

}
