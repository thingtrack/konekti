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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="SUPPLIER_GROUP")
public class SupplierGroup implements Serializable {
	@Id
	@Column(name="SUPPLIER_GROUP_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer supplierGroupId;
	
	@Column(name="NAME", nullable=false, unique=true, length=256)
	@Size(min=1, max=256)
	@NotNull
	private String name;
	
	@Column(name="DESCRIPTION", length=1024)
	@Size(min=1, max=1024)
	private String description;

	/**
	 * @param supplierGroupId the supplierGroupId to set
	 */
	public void setSupplierGroupId(Integer supplierGroupId) {
		this.supplierGroupId = supplierGroupId;
	}

	/**
	 * @return the supplierGroupId
	 */
	public Integer getSupplierGroupId() {
		return supplierGroupId;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((supplierGroupId == null) ? 0 : supplierGroupId.hashCode());
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
		if (!(obj instanceof SupplierGroup))
			return false;
		SupplierGroup other = (SupplierGroup) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (supplierGroupId == null) {
			if (other.supplierGroupId != null)
				return false;
		} else if (!supplierGroupId.equals(other.supplierGroupId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SupplierGroup [supplierGroupId=" + supplierGroupId + ", name="
				+ name + ", description=" + description + "]";
	}
	
}
