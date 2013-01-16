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
@Table(name="BULLETIN_TYPE")
public class BulletinType implements Serializable {
	@Id
	@Column(name="BULLETIN_TYPE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer bulletinTypeId;
	
	@Column(name="CODE", nullable=false, unique=true, length=64)
	@Size(min=1, max=64)
	@NotNull
	private String code;
	
	@Column(name="DESCRIPTION", length=512)
	@Size(min=1, max=1024)
	private String description;

	/**
	 * @param bulletinTypeId the bulletinTypeId to set
	 */
	public void setBulletinTypeId(int bulletinTypeId) {
		this.bulletinTypeId = bulletinTypeId;
	}

	/**
	 * @return the bulletinTypeId
	 */
	public int getBulletinTypeId() {
		return bulletinTypeId;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bulletinTypeId == null) ? 0 : bulletinTypeId.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		if (!(obj instanceof BulletinType))
			return false;
		BulletinType other = (BulletinType) obj;
		if (bulletinTypeId == null) {
			if (other.bulletinTypeId != null)
				return false;
		} else if (!bulletinTypeId.equals(other.bulletinTypeId))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BulletinType [bulletinTypeId=" + bulletinTypeId + ", code="
				+ code + ", description=" + description + "]";
	}

}
