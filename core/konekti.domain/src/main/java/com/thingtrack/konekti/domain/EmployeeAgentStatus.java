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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="EMPLOYEE_AGENT_STATUS")
public class EmployeeAgentStatus implements Serializable {
	@Id
	@Column(name="EMPLOYEE_AGENT_STATUS_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer employeeAgentStatusId;
	
	@Column(name="NAME", nullable=false, unique=true, length=64)
	private String name;
	
	@Column(name="DESCRIPTION", length=512)
	private String description;

	/**
	 * @param employeeAgentStatusId the employeeAgentStatusId to set
	 */
	public void setEmployeeAgentStatusId(Integer employeeAgentStatusId) {
		this.employeeAgentStatusId = employeeAgentStatusId;
	}

	/**
	 * @return the employeeAgentStatusId
	 */
	public Integer getEmployeeAgentStatusId() {
		return employeeAgentStatusId;
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
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((employeeAgentStatusId == null) ? 0 : employeeAgentStatusId
						.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (!(obj instanceof EmployeeAgentStatus))
			return false;
		EmployeeAgentStatus other = (EmployeeAgentStatus) obj;
		if (employeeAgentStatusId == null) {
			if (other.employeeAgentStatusId != null)
				return false;
		} else if (!employeeAgentStatusId.equals(other.employeeAgentStatusId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EmployeeAgentStatus [employeeAgentStatusId="
				+ employeeAgentStatusId + ", name=" + name + ", description="
				+ description + "]";
	}
}
