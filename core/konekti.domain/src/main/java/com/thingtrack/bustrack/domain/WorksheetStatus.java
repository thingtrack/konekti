package com.thingtrack.bustrack.domain;

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
@Table(name="WORKSHEET_STATUS")
public class WorksheetStatus implements Serializable {
	@Id
	@Column(name="WORKSHEET_STATUS_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer worksheetStatusId;
	
	@Column(name="CODE", nullable=false, unique=true, length=64)
	@Size(min=1, max=64)
	@NotNull
	private String code;
	
	@Column(name="DESCRIPTION", length=512)
	@Size(min=1, max=512)
	private String description;

	/**
	 * @return the worksheetStatusId
	 */
	public Integer getWorksheetStatusId() {
		return worksheetStatusId;
	}

	/**
	 * @param worksheetStatusId the worksheetStatusId to set
	 */
	public void setWorksheetStatusId(Integer worksheetStatusId) {
		this.worksheetStatusId = worksheetStatusId;
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
	 * @return the descriptionn
	 */
	public String getDescriptionn() {
		return description;
	}

	/**
	 * @param descriptionn the descriptionn to set
	 */
	public void setDescriptionn(String descriptionn) {
		this.description = descriptionn;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime
				* result
				+ ((worksheetStatusId == null) ? 0 : worksheetStatusId
						.hashCode());
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
		if (!(obj instanceof WorksheetStatus))
			return false;
		WorksheetStatus other = (WorksheetStatus) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (worksheetStatusId == null) {
			if (other.worksheetStatusId != null)
				return false;
		} else if (!worksheetStatusId.equals(other.worksheetStatusId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WorksheetStatus [worksheetStatusId=" + worksheetStatusId
				+ ", code=" + code + ", descriptionn=" + description + "]";
	}
}
