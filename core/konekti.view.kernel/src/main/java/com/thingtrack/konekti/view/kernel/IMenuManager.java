package com.thingtrack.konekti.view.kernel;

import java.util.Date;

import com.thingtrack.konekti.domain.Area;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.Location;
import com.thingtrack.konekti.domain.User;

public interface IMenuManager {
	public User getUser();
	public void setUser(User user);
	public void setContext(IWorkbenchContext context);
	public void addMessage(Organization organization, Location location, Area area, User user, String payload, Date messageDate);
}
