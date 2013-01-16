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
import javax.persistence.Table;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="OFFER_REQUEST_STATUS")
public class OfferRequestStatus implements Serializable {
	@Id
	@Column(name="OFFER_REQUEST_STATUS_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer offerRequestStatusId;
	
	@Column(name="CODE", nullable=false, unique=true, length=64)
	private String code;
	
	@Column(name="DESCRIPTION", length=512)
	private String description;

	public enum OFFER_REQUEST_STATUS {        
        OPEN,
        PENDING,
        TRANSFERRED,
        REJECTED,
        CLOSE
    }
	
	/**
	 * @param offerRequestStatusId the offerRequestStatusId to set
	 */
	public void setOfferRequestStatusId(Integer offerRequestStatusId) {
		this.offerRequestStatusId = offerRequestStatusId;
	}

	/**
	 * @return the offerRequestStatusId
	 */
	public Integer getOfferRequestStatusId() {
		return offerRequestStatusId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((offerRequestStatusId == null) ? 0 : offerRequestStatusId
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof OfferRequestStatus))
			return false;
		OfferRequestStatus other = (OfferRequestStatus) obj;
		if (offerRequestStatusId == null) {
			if (other.offerRequestStatusId != null)
				return false;
		} else if (!offerRequestStatusId.equals(other.offerRequestStatusId))
			return false;
		return true;
	}

}
