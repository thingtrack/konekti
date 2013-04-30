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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="ORGANIZATION")
public class Organization implements Serializable {
	@Id
	@Column(name="ORGANIZATION_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer organizationId;

	@Column(name="CODE", nullable=false, unique=true, length=64)
	private String code;

	@Column(name="NAME", nullable=false, length=64)
	private String name;

	@Column(name="DESCRIPTION", length=512)
	private String description;

	@Column(name="CIF", length=32)
	private String cif;

	@ManyToOne(optional=false)
	@JoinColumn(name="ORGANIZATION_TYPE_ID", nullable=false)	
	private OrganizationType organizationType;

	@OneToOne(optional=false)
	@JoinColumn(name="SOCIAL_ADDRESS_ID", nullable=false)
	private Address socialAddress = new Address();

	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH })
	@JoinTable(name="ORGANIZATION_LOCATION",
			   joinColumns=@JoinColumn(name="ORGANIZATION_ID"),
			   inverseJoinColumns=@JoinColumn(name="LOCATION_ID"))	
	private List<Location> locations = new ArrayList<Location>();

	@Column(name="COMMENT", length=512)
	private String comment;

	@Column(name="ACTIVE", nullable=false)
	private Boolean active=true;

	public Organization() {

	}

	public Organization(String code, OrganizationType organizationType, Address socialAddress) {
		this(code, organizationType, socialAddress, true);

	}

	public Organization(String code, OrganizationType organizationType, Address socialAddress, Boolean active) {
		this.code = code;
		this.organizationType = organizationType;
		this.socialAddress = socialAddress;
		this.active = active;

	}

	/**
	 * @param organizationId the organizationId to set
	 */
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	/**
	 * @return the organizationId
	 */
	public Integer getOrganizationId() {
		return organizationId;
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

	/**
	 * @param cif the cif to set
	 */
	public void setCif(String cif) {
		this.cif = cif;
	}

	/**
	 * @return the cif
	 */
	public String getCif() {
		return cif;
	}

	/**
	 * @param socialAddress the socialAddress to set
	 */
	public void setSocialAddress(Address socialAddress) {
		this.socialAddress = socialAddress;
	}

	/**
	 * @return the socialAddress
	 */
	public Address getSocialAddress() {
		return socialAddress;
	}

	/**
	 * @param locations the locations to set
	 */
	public void addLocation(Location location) {
		locations.add(location);

		if (!location.getOrganizations().contains(this))
			location.addOrganization(this);

	}

	public void removeLocation(Location location) {
		locations.remove(location);
		
	}
	
	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	/**
	 * @return the locations
	 */
	public List<Location> getLocations() {
		return Collections.unmodifiableList(locations);		
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * @return the active
	 */
	public Boolean getActive() {
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

	/**
	 * @param organizationType the organizationType to set
	 */
	public void setOrganizationType(OrganizationType organizationType) {
		this.organizationType = organizationType;
	}

	/**
	 * @return the organizationType
	 */
	public OrganizationType getOrganizationType() {
		return organizationType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result
				+ ((organizationId == null) ? 0 : organizationId.hashCode());
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
		if (!(obj instanceof Organization))
			return false;
		Organization other = (Organization) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (organizationId == null) {
			if (other.organizationId != null)
				return false;
		} else if (!organizationId.equals(other.organizationId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Organization [organizationId=" + organizationId + ", code="
				+ code + "]";
	}

}
