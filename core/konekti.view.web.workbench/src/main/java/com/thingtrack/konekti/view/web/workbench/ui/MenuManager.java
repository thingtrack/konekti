package com.thingtrack.konekti.view.web.workbench.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.domain.User;
import com.thingtrack.konekti.view.kernel.IMenuManager;
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

}
