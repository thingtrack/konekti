package com.thingtrack.konekti.view.web.workbench.ui;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.domain.Area;
import com.thingtrack.konekti.domain.Location;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.User;
import com.thingtrack.konekti.view.kernel.IMenuManager;
import com.thingtrack.konekti.view.kernel.IWorkbenchContext;
import com.thingtrack.konekti.view.layout.KonektiLayout;

public class MenuManager implements IMenuManager {
	@Autowired
	private KonektiLayout konektiLayout;
		
	@Override
	public User getUser() {
		return konektiLayout.getMenuLayout().getUser();
	}
	
	@Override
	public void setUser(User user) {
		konektiLayout.getMenuLayout().setUser(user);
		
	}

	@Override
	public void setContext(IWorkbenchContext context) {
		konektiLayout.getMenuLayout().setContext(context);
		
	}
	
	@Override
	public void addMessage(Organization organization, Location location, Area area, User user, String payload, Date messageDate) {
		konektiLayout.getMenuLayout().addMessage(organization, location, area, user, payload, messageDate);
		
	}
	
}
