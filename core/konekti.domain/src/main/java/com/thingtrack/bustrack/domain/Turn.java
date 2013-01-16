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
package com.thingtrack.bustrack.domain;

import java.io.Serializable;
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

import com.thingtrack.konekti.domain.Service;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="TURN")
public class Turn implements Serializable {
	@Id
	@Column(name="TURN_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer turnId;
	
	@Column(name="CODE", unique=true, length=64)
	private String code;
	
	@Column(name="DESCRIPTION", length=512)
	private String description;
	
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(name="TURN_SERVICE",
			   joinColumns=@JoinColumn(name="TURN_ID"),
			   inverseJoinColumns=@JoinColumn(name="SERVICE_ID"))	
	private List<Service> services;

	public Turn() {
		
	}
	
	public Turn(String code) {
		this.code = code;
		
	}
	
	/**
	 * @return the turnId
	 */
	public Integer getTurnId() {
		return turnId;
	}

	/**
	 * @param turnId the turnId to set
	 */
	public void setTurnId(Integer turnId) {
		this.turnId = turnId;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the services
	 */
	public List<Service> getServices() {
		return Collections.unmodifiableList(services);
		
	}

	/**
	 * @param services the services to set
	 */
	public void setServices(List<Service> services) {			
		this.services = services;
		
	}

	public void addService(Service service) {
		services.add(service);
		
		if (!service.getTurns().contains(this))
			service.addTurn(this);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((turnId == null) ? 0 : turnId.hashCode());
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
		if (!(obj instanceof Turn))
			return false;
		Turn other = (Turn) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (turnId == null) {
			if (other.turnId != null)
				return false;
		} else if (!turnId.equals(other.turnId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Turn [turnId=" + turnId + ", code=" + code + "]";
	}

}
