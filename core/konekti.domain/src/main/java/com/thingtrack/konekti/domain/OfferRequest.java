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
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="OFFER_REQUEST")
public class OfferRequest implements Serializable {
	@Id
	@Column(name="OFFER_REQUEST_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer offerRequestId;
	
	@Column(name="CODE", nullable=false, unique=true, length=64)
	private String code;
	
	@ManyToOne
	@JoinColumn(name="ORGANIZATION_ID", nullable=false)
	private Organization organization;
	
	@ManyToOne
	@JoinColumn(name="OFFER_REQUEST_TYPE_ID", nullable=false)
	private OfferRequestType offerRequestType;
	
	@ManyToOne
	@JoinColumn(name="CLIENT_ID", nullable=false)
	private Client client;

	@OneToMany(mappedBy="offerRequest")
	private List<OfferRequestLine> offerRequestLines;
	
	@OneToMany(mappedBy="offerRequest")
	private List<Offer> offers;
	
	@Column(name="OBSERVATION", length=512)
	private String observation;
	
	@ManyToOne
	@JoinColumn(name="OFFER_REQUEST_STATUS_ID", nullable=false)
	private OfferRequestStatus offerRequestStatus;
	
	@Column(name="OFFER_REQUEST_DATE", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date offerRequestDate;

	public enum STATUS {
		OPENED,		
		PENDING,
		TRANSFERRED,
		REJECTED,
		CLOSED
	}
	
	public enum TYPE {
		OTHER
	}
	
	/**
	 * @param offerRequestId the offerRequestId to set
	 */
	public void setOfferRequestId(Integer offerRequestId) {
		this.offerRequestId = offerRequestId;
	}

	/**
	 * @return the offerRequestId
	 */
	public Integer getOfferRequestId() {
		return offerRequestId;
	}

	/**
	 * @param offerRequestType the offerRequestType to set
	 */
	public void setOfferRequestType(OfferRequestType offerRequestType) {
		this.offerRequestType = offerRequestType;
	}

	/**
	 * @return the offerRequestType
	 */
	public OfferRequestType getOfferRequestType() {
		return offerRequestType;
	}

	/**
	 * @param offers the offers to set
	 */
	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	/**
	 * @return the offers
	 */
	public List<Offer> getOffers() {
		return offers;
	}

	/**
	 * @param offerRequestStatus the offerRequestStatus to set
	 */
	public void setOfferRequestStatus(OfferRequestStatus offerRequestStatus) {
		this.offerRequestStatus = offerRequestStatus;
	}

	/**
	 * @return the offerRequestStatus
	 */
	public OfferRequestStatus getOfferRequestStatus() {
		return offerRequestStatus;
	}

	/**
	 * @param offerRequestDate the offerRequestDate to set
	 */
	public void setOfferRequestDate(Date offerRequestDate) {
		this.offerRequestDate = offerRequestDate;
	}

	/**
	 * @return the offerRequestDate
	 */
	public Date getOfferRequestDate() {
		return offerRequestDate;
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
				+ ((offerRequestId == null) ? 0 : offerRequestId.hashCode());
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
		if (!(obj instanceof OfferRequest))
			return false;
		OfferRequest other = (OfferRequest) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (offerRequestId == null) {
			if (other.offerRequestId != null)
				return false;
		} else if (!offerRequestId.equals(other.offerRequestId))
			return false;
		return true;
	}

	/**
	 * @param organization the organization to set
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	/**
	 * @return the organization
	 */
	public Organization getOrganization() {
		return organization;
	}

	/**
	 * @param observation the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}

	/**
	 * @return the observation
	 */
	public String getObservation() {
		return observation;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OfferRequest [offerRequestId=" + offerRequestId + ", code="
				+ code + ", organization=" + organization
				+ ", offerRequestType=" + offerRequestType
				+ ", offers=" + offers
				+ ", observation=" + observation + ", offerRequestStatus="
				+ offerRequestStatus + ", offerRequestDate=" + offerRequestDate
				+ "]";
	}

	/**
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * @return the offerRequestLines
	 */
	public List<OfferRequestLine> getOfferRequestLines() {
		return offerRequestLines;
	}

	/**
	 * @param offerRequestLines the offerRequestLines to set
	 */
	public void setOfferRequestLines(List<OfferRequestLine> offerRequestLines) {
		this.offerRequestLines = offerRequestLines;
	}
	
}
