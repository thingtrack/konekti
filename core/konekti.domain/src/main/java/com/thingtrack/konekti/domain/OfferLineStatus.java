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
@Table(name="OFFER_LINE_STATUS")
public class OfferLineStatus implements Serializable {
	@Id
	@Column(name="OFFER_LINE_STATUS_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer offerLineStatusId;
		
	@Column(name="CODE", nullable=false, unique=true, length=64)
	@Size(min=1, max=64)
	@NotNull
	private String code;
	
	@Column(name="DESCRIPTION", length=512)
	@Size(min=1, max=512)
	private String description;

	/**
	 * @return the offerLineStatusId
	 */
	public Integer getOfferLineStatusId() {
		return offerLineStatusId;
	}

	/**
	 * @param offerLineStatusId the offerLineStatusId to set
	 */
	public void setOfferLineStatusId(Integer offerLineStatusId) {
		this.offerLineStatusId = offerLineStatusId;
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
		result = prime
				* result
				+ ((offerLineStatusId == null) ? 0 : offerLineStatusId
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
		if (!(obj instanceof OfferLineStatus))
			return false;
		OfferLineStatus other = (OfferLineStatus) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (offerLineStatusId == null) {
			if (other.offerLineStatusId != null)
				return false;
		} else if (!offerLineStatusId.equals(other.offerLineStatusId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OfferLineStatus [offerLineStatusId=" + offerLineStatusId
				+ ", code=" + code + ", description=" + description + "]";
	}
}
