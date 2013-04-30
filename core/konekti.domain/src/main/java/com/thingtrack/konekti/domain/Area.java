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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="AREA")
public class Area implements Serializable  {
	@Id
	@Column(name="AREA_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer areaId;
	
	@Column(name="CODE", nullable=false, unique=true, length=64)
	private String code;
	
	@Column(name="NAME", length=64)
	private String name;
	
	@Column(name="DESCRIPTION", length=512)
	private String description;

	@ManyToOne
	@JoinColumn(name="AREA_TYPE_ID", nullable=false)
	private AreaType areaType;
	
	@ManyToOne
	@JoinColumn(name="LOCATION_ID", nullable=false)
	private Location location;
	
	@Column(name="COMMENT", length=512)
	private String Comment;
	
	@Column(name="ACTIVE", nullable=false)
	private Boolean active=true;

	public Area() {
		
	}

	public Area(String code, AreaType areaType) {
		this(code, areaType, true);
		
	}
	
	public Area(String code, AreaType areaType, Boolean active) {
		this.code = code;
		this.areaType = areaType;
		this.active = active;		
		
	}
	
	/**
	 * @param areaId the areaId to set
	 */
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	/**
	 * @return the areaId
	 */
	public Integer getAreaId() {
		return areaId;
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
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		Comment = comment;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return Comment;
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
	 * @param areaType the areaType to set
	 */
	public void setAreaType(AreaType areaType) {
		this.areaType = areaType;
	}

	/**
	 * @return the areaType
	 */
	public AreaType getAreaType() {
		return areaType;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
		
		if (!location.getAreas().contains(this)) {
			location.addArea(this);
		}
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
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
				+ ((areaId == null) ? 0 : areaId.hashCode());
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
		if (!(obj instanceof Area))
			return false;
		Area other = (Area) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (areaId == null) {
			if (other.areaId != null)
				return false;
		} else if (!areaId.equals(other.areaId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Area [areaId=" + areaId + ", code=" + code + "]";
	}

}
