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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Thingtrack S.L.
 *
 */
@Entity
@Table(name="SERVICE_STATUS")
public class ServiceStatus {
	@Id
	@Column(name="SERVICE_STATUS_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer serviceStatusId;
	
	@Column(name="CODE", nullable=false, unique=true, length=256)
	@Size(min=1, max=256)
	@NotNull
	private String code;
	
	@Column(name="DESCRIPTION", length=1024)
	@Size(min=1, max=1024)
	private String description;

	
	public enum STATUS {
		
		OPENED,
		EXECUTING,
		COMPLETED,
		ASSIGNED,
		CANCELLED,
		STOPPED;
	}
	/**
	 * @param serviceStatusId the serviceStatusId to set
	 */
	public void setServiceStatusId(Integer serviceStatusId) {
		this.serviceStatusId = serviceStatusId;
	}

	/**
	 * @return the serviceStatusId
	 */
	public Integer getServiceStatusId() {
		return serviceStatusId;
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
	
	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ServiceStatus))
			return false;
		ServiceStatus other = (ServiceStatus) obj;
		if (serviceStatusId == null) {
			if (other.serviceStatusId != null)
				return false;
		} else if (!serviceStatusId.equals(other.serviceStatusId))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
}
