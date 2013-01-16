package com.thingtrack.konekti.domain;

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
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="MENU_WORKBENCH")
public class MenuWorkbench implements Serializable {
	@Id
	@Column(name="MENU_WORKBENCH_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer menuWorkbenchId;

	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="ACTIVE")
	private boolean active;
	
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
