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
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Thingtrack S.L
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="USER")
public class User implements Serializable {
	@Id
	@Column(name="USER_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;
	
	@Column(name="USER_NAME", nullable=false, unique=true, length=32)
	private String username;
	
	@Column(name="PASSWORD", nullable=false, length=32			)
	private String password;
		
	@ManyToMany
	@JoinTable(name="USER_ROLE",
			   joinColumns=@JoinColumn(name="USER_ID"),
			   inverseJoinColumns=@JoinColumn(name="ROLE_ID"))
	private List<Role> roles;

	@Column(name="DEFAULT_LOCALE")
	private String defaultLocale;
	
	@Column(name="COMMENT", length=256)
	private String comment;
	
	@Column(name="ACTIVE", nullable=false)
	private boolean active=true;

	@OneToOne(mappedBy="user")
	private EmployeeAgent employeeAgent;

	@OneToOne(mappedBy="user")
	private Client client;

	@OneToOne(mappedBy="user")
	private Supplier supplier;
	
	public User() {
		
	}
	
	public User(String username, String password, List<Role> roles,
			String comment, boolean active) {
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.comment = comment;
		this.active = active;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
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

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username
				+ ", password=" + password + ", comment="
				+ comment + ", active=" + active + "]";
	}

	/**
	 * @return the defaultLocale
	 */
	public String getDefaultLocale() {
		return defaultLocale;
	}

	/**
	 * @param defaultLocale the defaultLocale to set
	 */
	public void setDefaultLocale(String defaultLocale) {
		this.defaultLocale = defaultLocale;
	}

	/**
	 * @return the employeeAgent
	 */
	public EmployeeAgent getEmployeeAgent() {
		return employeeAgent;
	}

	/**
	 * @param employeeAgent the employeeAgent to set
	 */
	public void setEmployeeAgent(EmployeeAgent employeeAgent) {
		this.employeeAgent = employeeAgent;
	}

	/**
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * @return the supplier
	 */
	public Supplier getSupplier() {
		return supplier;
	}

	/**
	 * @param supplier the supplier to set
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

}
