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
@Table(name="AREA_TYPE")
public class AreaType implements Serializable {
	@Id
	@Column(name="AREA_TYPE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer areaTypeId;
	
	@Column(name="NAME", nullable=false, unique=true, length=256)
	@Size(min=1, max=256)
	@NotNull
	private String name;
	
	@Column(name="DESCRIPTION", length=1024)
	@Size(min=1, max=1024)
	private String description;

	/**
	 * @param areaTypeId the areaTypeId to set
	 */
	public void setAreaTypeId(Integer areaTypeId) {
		this.areaTypeId = areaTypeId;
	}

	/**
	 * @return the areaTypeId
	 */
	public Integer getAreaTypeId() {
		return areaTypeId;
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
				+ ((areaTypeId == null) ? 0 : areaTypeId.hashCode());
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
		if (!(obj instanceof AreaType))
			return false;
		AreaType other = (AreaType) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (areaTypeId == null) {
			if (other.areaTypeId != null)
				return false;
		} else if (!areaTypeId.equals(other.areaTypeId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AreaType [areaTypeId=" + areaTypeId + ", name="
				+ name + ", description=" + description + "]";
	}
}
