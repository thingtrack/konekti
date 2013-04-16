package com.thingtrack.konekti.view.kernel.ui.layout;

import com.thingtrack.konekti.domain.User;

/**
 * @author Thingtrack S.L.
 *
 */
public interface IHeaderLayout {	
	public void setKonektiVersion(String version);
	
	public User getUser();
	public void setUser(User user);
}
