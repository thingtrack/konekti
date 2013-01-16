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
 * @author Thigntrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="OFFER_REQUEST_TYPE")
public class OfferRequestType implements Serializable {
	@Id
	@Column(name="OFFER_REQUEST_TYPE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer offerRequestTypeId;
	
	@Column(name="CODE", nullable=false, unique=true, length=64)
	private String code;
	
	@Column(name="DESCRIPTION", length=512)
	private String description;

	/**
	 * @param offerRequestTypeId the offerRequestTypeId to set
	 */
	public void setOfferRequestTypeId(Integer offerRequestTypeId) {
		this.offerRequestTypeId = offerRequestTypeId;
	}

	/**
	 * @return the offerRequestTypeId
	 */
	public Integer getOfferRequestTypeId() {
		return offerRequestTypeId;
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
				+ ((offerRequestTypeId == null) ? 0 : offerRequestTypeId
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof OfferRequestType))
			return false;
		OfferRequestType other = (OfferRequestType) obj;
		if (offerRequestTypeId == null) {
			if (other.offerRequestTypeId != null)
				return false;
		} else if (!offerRequestTypeId.equals(other.offerRequestTypeId))
			return false;
		return true;
	}

}
