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
@Table(name="OFFER_REQUEST_LINE_STATUS")
public class OfferRequestLineStatus implements Serializable {
	@Id
	@Column(name="OFFER_REQUEST_LINE_STATUS_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer offerRequestLineStatusId;
	
	@Column(name="CODE", nullable=false, unique=true, length=64)
	@Size(min=1, max=64)
	@NotNull
	private String code;
	
	@Column(name="DESCRIPTION", length=512)
	@Size(min=1, max=512)
	private String description;
 
	/**
	 * @return the offerRequestLineStatusId
	 */
	public Integer getOfferRequestLineStatusId() {
		return offerRequestLineStatusId;
	}

	/**
	 * @param offerRequestLineStatusId the offerRequestLineStatusId to set
	 */
	public void setOfferRequestLineStatusId(Integer offerRequestLineStatusId) {
		this.offerRequestLineStatusId = offerRequestLineStatusId;
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
				+ ((offerRequestLineStatusId == null) ? 0
						: offerRequestLineStatusId.hashCode());
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
		if (!(obj instanceof OfferRequestLineStatus))
			return false;
		OfferRequestLineStatus other = (OfferRequestLineStatus) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (offerRequestLineStatusId == null) {
			if (other.offerRequestLineStatusId != null)
				return false;
		} else if (!offerRequestLineStatusId
				.equals(other.offerRequestLineStatusId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OfferRequestLineStatus [offerRequestLineStatusId="
				+ offerRequestLineStatusId + ", code=" + code
				+ ", description=" + description + "]";
	}
}
