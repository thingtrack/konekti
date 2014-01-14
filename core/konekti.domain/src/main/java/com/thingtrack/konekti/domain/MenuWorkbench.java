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

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * Entity class
 * <p>
 * Menu Workbench
 * <p>
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="MENU_WORKBENCH")
public class MenuWorkbench implements Serializable {
	
	/**
	 * Unique identifier
	 */
	@Id
	@Column(name="MENU_WORKBENCH_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer menuWorkbenchId;

	/**
	 * Description
	 */
	@Column(name="DESCRIPTION")
	private String description;
	
	/**
	 * Active
	 */
	@Column(name="ACTIVE")
	private boolean active;
	
	/**
	 * {@link MenuFolderResource}
	 */
	@OneToMany(mappedBy="menuWorkbench")
	@OrderBy("position")
	private List<MenuFolderResource> menuFolderResources;
	
	/**
	 * @return the menuWorkbenchId
	 */
	public Integer getMenuWorkbenchId() {
		return menuWorkbenchId;
	}

	/**
	 * @param menuWorkbenchId the menuWorkbenchId to set
	 */
	public void setMenuWorkbenchId(Integer menuWorkbenchId) {
		this.menuWorkbenchId = menuWorkbenchId;
	}

	/**
	 * @param menuFolderResource the menuFolderResource to set
	 */
	public void setMenuFolderResource(List<MenuFolderResource> menuFolderResources) {
		this.menuFolderResources = menuFolderResources;
	}

	/**
	 * @return the menuFolderResource
	 */
	public List<MenuFolderResource> getMenuFolderResource() {
		return menuFolderResources;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}
	
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}	
	
}
