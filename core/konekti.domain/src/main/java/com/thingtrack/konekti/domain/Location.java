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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="LOCATION")
public class Location implements Serializable {
	@Id
	@Column(name="LOCATION_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer locationId;

	@Column(name="CODE", nullable=false, unique=true, length=64)
	private String code;

	@Column(name="NAME", nullable=false, unique=true, length=64)
	private String name;	

	@Column(name="DESCRIPTION", length=512)
	private String description;

	@Column(name="CIF", length=32)
	private String cif;
	
	@ManyToOne
	@JoinColumn(name="LOCATION_ADDRESS_ID")	
	private Address locationAddress = new Address();

	@ManyToMany(mappedBy="locations")
	private List<Organization> organizations = new ArrayList<Organization>();

	@ManyToOne
	@JoinColumn(name="LOCATION_TYPE_ID", nullable=false)	
	private LocationType locationType;

	@OneToMany(mappedBy="location")			
	private List<Area> areas = new ArrayList<Area>();		

	@Column(name="COMMENT", length=512)
	private String comment;

	@Column(name="ACTIVE", nullable=false)
	private Boolean active=true;

	public Location() {
	}

	public Location(String code, String name, LocationType locationType) {
		this(code, name, locationType, true);

	}	

	public Location(String code, String name, LocationType locationType, Boolean active) {
		this.code = code;
		this.name = name;
		this.locationType = locationType;
		this.active = active;

	}	

	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	/**
	 * @return the locationId
	 */
	public Integer getLocationId() {
		return locationId;
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
	 * @param locationType the locationType to set
	 */
	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}

	/**
	 * @return the locationType
	 */
	public LocationType getLocationType() {
		return locationType;
	}

	public void addArea(Area area) {
		areas.add(area);

		if (area.getLocation() != this)
			area.setLocation(this);
	}

	public void removeArea(Area area) {
		areas.remove(area);
		
		if (area.getLocation().equals(this))
			area.setLocation(null);
	}
	
	/**
	 * @return the areas
	 */
	public List<Area> getAreas() {
		return Collections.unmodifiableList(areas);
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
	 * @param locationAddress the locationAddress to set
	 */
	public void setLocationAddress(Address locationAddress) {
		this.locationAddress = locationAddress;
	}

	/**
	 * @return the locationAddress
	 */
	public Address getLocationAddress() {
		return locationAddress;
	}

	/**
	 * @param organizations the organizations to set
	 */
	public void addOrganization(Organization organization) {
		organizations.add(organization);

		/*if (!organization.getLocations().contains(this))
			organization.addLocation(this);*/
	}

	/**
	 * @return the organizations
	 */
	public List<Organization> getOrganizations() {
		return Collections.unmodifiableList(organizations);
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
				+ ((locationId == null) ? 0 : locationId.hashCode());
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
		if (!(obj instanceof Location))
			return false;
		Location other = (Location) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (locationId == null) {
			if (other.locationId != null)
				return false;
		} else if (!locationId.equals(other.locationId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Location [locationId=" + locationId + ", code=" + code + "]";
	}

	/**
	 * @return the cif
	 */
	public String getCif() {
		return cif;
	}

	/**
	 * @param cif the cif to set
	 */
	public void setCif(String cif) {
		this.cif = cif;
	}

}
