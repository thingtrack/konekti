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
@Table(name="WORKSHOP_TYPE")
public class WorkshopType implements Serializable {
	@Id
	@Column(name="WORKSHOP_TYPE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer workshopTypeId;
	
	@Column(name="CODE", nullable=false, unique=true, length=256)
	@Size(min=1, max=256)
	@NotNull
	private String code;
	
	@Column(name="DESCRIPTION", length=1024)
	@Size(min=1, max=1024)
	private String description;

	/**
	 * @return the workshopTypeId
	 */
	public Integer getWorkshopTypeId() {
		return workshopTypeId;
	}

	/**
	 * @param workshopTypeId the workshopTypeId to set
	 */
	public void setWorkshopTypeId(Integer workshopTypeId) {
		this.workshopTypeId = workshopTypeId;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result
				+ ((workshopTypeId == null) ? 0 : workshopTypeId.hashCode());
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
		if (!(obj instanceof WorkshopType))
			return false;
		WorkshopType other = (WorkshopType) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (workshopTypeId == null) {
			if (other.workshopTypeId != null)
				return false;
		} else if (!workshopTypeId.equals(other.workshopTypeId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WorkshopType [workshopTypeId=" + workshopTypeId + ", code="
				+ code + ", description=" + description + "]";
	}
}
