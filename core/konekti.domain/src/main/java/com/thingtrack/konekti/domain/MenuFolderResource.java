package com.thingtrack.konekti.domain;

/*
 * #%L
 * Konekti Domain Layer
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2010 - 2014 Thingtrack s.l.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("FOLDER")
public class MenuFolderResource extends MenuResource {
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "MENU_RESOURCE_FOLDER", 
			   joinColumns = @JoinColumn(name = "MENU_RESOURCE_ID", referencedColumnName = "MENU_RESOURCE_ID"), 
			                 inverseJoinColumns = @JoinColumn(name = "SUB_MENU_RESOURCE_ID", referencedColumnName = "MENU_RESOURCE_ID"))
	@OrderBy("position")			                 
	private List<MenuResource> menuResources;

	@ManyToOne
	@JoinColumn(name="MENU_WORKBENCH_ID")	
	private MenuWorkbench menuWorkbench;
	/**
	 * @return the menuResources
	 */
	public List<MenuResource> getMenuResources() {
		return menuResources;
	}

	/**
	 * @param menuResources the menuResources to set
	 */
	public void setMenuResources(List<MenuResource> menuResources) {
		this.menuResources = menuResources;
	}

	/**
	 * @param menuWorkbench the menuWorkbench to set
	 */
	public void setMenuWorkbench(MenuWorkbench menuWorkbench) {
		this.menuWorkbench = menuWorkbench;
	}

	/**
	 * @return the menuWorkbench
	 */
	public MenuWorkbench getMenuWorkbench() {
		return menuWorkbench;
	}
}
