/*
 * Copyright 2011 Thingtrack, S.L.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.thingtrack.konekti.view.kernel.ui.layout;

import java.util.List;

import com.thingtrack.konekti.domain.User;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;

/**
 * @author Thingtrack S.L.
 *
 */
public interface IMenuLayout {
	public void removeMenuBar();
	public MenuItem getMenuItem(String name);
	public MenuItem addMenuItem(String caption, Command command);
	public MenuItem addMenuItem(String caption, MenuItem menuItem, Command command);
	public MenuItem addMenuItem(String caption, String hint, MenuItem menuItem, Command command);
	public MenuItem addMenuItem(String caption, Resource icon, Command command);
	public MenuItem addMenuItem(String caption, Resource icon, MenuItem menuItem, Command command);
	public MenuItem addMenuItem(String caption, String hint, Resource icon,  MenuItem menuItem, Command command);	
	public List<MenuItem> getItems();
	public void removeItem(MenuItem menuItem);
	public void removeItems();
	public boolean getCollapse();
	public int getSize();
	public User getUser();
	public void setUser(User user);
	public void addListenerUserChange(IUserChangeListener listener);
}
