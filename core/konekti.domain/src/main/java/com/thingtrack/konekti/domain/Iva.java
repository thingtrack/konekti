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
@Table(name="IVA")
public class Iva implements Serializable {
	@Id
	@Column(name="IVA_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer ivaId;
	
	@Column(name="CODE", nullable=false, unique=true, length=64)
	private String code;
	
	@Column(name="DESCRIPTION", length=512)
	private String description;
	
	@Column(name="PERCENT", length = 5, precision = 2)
	private double percent;

	/**
	 * @return the ivaId
	 */
	public Integer getIvaId() {
		return ivaId;
	}

	/**
	 * @param ivaId the ivaId to set
	 */
	public void setIvaId(Integer ivaId) {
		this.ivaId = ivaId;
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
	 * @return the percent
	 */
	public double getPercent() {
		return percent;
	}

	/**
	 * @param percent the percent to set
	 */
	public void setPercent(double percent) {
		this.percent = percent;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((ivaId == null) ? 0 : ivaId.hashCode());
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
		if (!(obj instanceof Iva))
			return false;
		Iva other = (Iva) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (ivaId == null) {
			if (other.ivaId != null)
				return false;
		} else if (!ivaId.equals(other.ivaId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Iva [ivaId=" + ivaId + ", code=" + code + ", description="
				+ description + ", percent=" + percent + "]";
	}
	
}
