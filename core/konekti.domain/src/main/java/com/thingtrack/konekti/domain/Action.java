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
package com.thingtrack.konekti.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="ACTION")
public class Action implements Serializable {
	@Id
	@Column(name="ACTION_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer actionId;
	
	@Column(name="CODE", length=64, unique=true, nullable=false)
	private String code;
	
	@Column(name="DESCRIPTION", length=256)
	private String description;
	
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(name="ACTION_RESOURCE",
			   joinColumns=@JoinColumn(name="ACTION_ID"),
			   inverseJoinColumns=@JoinColumn(name="RESOURCE_ID"))
	private List<Resource> resources = new ArrayList<Resource>();

	@ManyToMany(mappedBy="actions", cascade=CascadeType.PERSIST)
	private List<Permission> permissions;
	
	@Column(name="COMMENT", length=512)
	private String comment;
	
	@Column(name="ACTIVE", nullable=false)
	private boolean active = true;
	
	public Action() {
		
	}
	
	public Action(String code) {		
		this.code = code;
		
	}
	
	public Action(String code, boolean active) {		
		this.code = code;
		this.active = active;
		
	}
	
	/**
	 * @param actionId the actionId to set
	 */
	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	/**
	 * @return the actionId
	 */
	public Integer getActionId() {
		return actionId;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
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

	/**
	 * @return the permissions
	 */
	public List<Permission> getPermissions() {
		return Collections.unmodifiableList(permissions);
	}

	public void addPermission(Permission permission) {
		permissions.add(permission);
		
		
		//if (!permission.getActions().contains(this))
			//permission.addTurn(this);
	}
	
	/**
	 * @return the resources
	 */
	public List<Resource> getResources() {
		return Collections.unmodifiableList(resources);
	}

	public void addResouce(Resource resource) {
		resources.add(resource);
		
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
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((actionId == null) ? 0 : actionId.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Action))
			return false;
		Action other = (Action) obj;
		if (actionId == null) {
			if (other.actionId != null)
				return false;
		} else if (!actionId.equals(other.actionId))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Action [actionId=" + actionId + ", code=" + code + ", active="
				+ active + "]";
	}

}
