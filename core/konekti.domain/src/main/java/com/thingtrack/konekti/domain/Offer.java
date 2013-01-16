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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name="OFFER")
public class Offer implements Serializable {
	@Id
	@Column(name="OFFER_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer offerId;
	
	@ManyToOne
	@JoinColumn(name="ORGANIZATION_ID", nullable=false)
	private Organization organization;
	
	@Column(name="CODE", unique=true, nullable=false) 
	private String code;
		
	@Column(name="REVISION")
	private String revision;
	
	@ManyToOne
	@JoinColumn(name="OFFER_TYPE_ID", nullable=false)
	private OfferType offerType;
	
	@ManyToOne
	@JoinColumn(name="CLIENT_ID", nullable=false)
	private Client offerClient;
	
	@Column(name="PRICE")
	private float price;
	
	@Column(name="DISCOUNT")
	private float discount;
		
	@OneToMany(mappedBy="offer")
	private List<OfferLine> offerLines = new ArrayList<OfferLine>();
		
	@ManyToMany
	@JoinTable(name="OFFER_INVOICE",
			   joinColumns=@JoinColumn(name="OFFER_ID"),
			   inverseJoinColumns=@JoinColumn(name="INVOICE_ID"))	
	private List<Invoice> invoices;
	
	@ManyToOne
	@JoinColumn(name="OFFER_REQUEST_ID")
	private OfferRequest offerRequest;
	
	@Column(name="OBSERVATION")
	private String observation;
	
	@ManyToOne
	@JoinColumn(name="OFFER_STATUS_ID", nullable=false)
	private OfferStatus offerStatus;
	
	@Column(name="OFFER_DATE", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date offerDate;

	public enum STATUS {        
        OPENED,
        SENT,
        ACCEPTED,
        REJECTED,
        CLOSED
    }

	public enum TYPE {
		OTHERS
	}
	/**
	 * @param offerId the offerId to set
	 */
	public void setOfferId(Integer offerId) {
		this.offerId = offerId;
	}

	/**
	 * @return the offerId
	 */
	public Integer getOfferId() {
		return offerId;
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
	 * @param revision the revision to set
	 */
	public void setRevision(String revision) {
		this.revision = revision;
	}

	/**
	 * @return the revision
	 */
	public String getRevision() {
		return revision;
	}

	/**
	 * @param offerType the offerType to set
	 */
	public void setOfferType(OfferType offerType) {
		this.offerType = offerType;
	}

	/**
	 * @return the offerType
	 */
	public OfferType getOfferType() {
		return offerType;
	}

	/**
	 * @param offerClient the offerClient to set
	 */
	public void setOfferClient(Client offerClient) {
		this.offerClient = offerClient;
	}

	/**
	 * @return the offerClient
	 */
	public Client getOfferClient() {
		return offerClient;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(float discount) {
		this.discount = discount;
	}

	/**
	 * @return the discount
	 */
	public float getDiscount() {
		return discount;
	}

	/**
	 * @param offerStatus the offerStatus to set
	 */
	public void setOfferStatus(OfferStatus offerStatus) {
		this.offerStatus = offerStatus;
	}

	/**
	 * @return the offerStatus
	 */
	public OfferStatus getOfferStatus() {
		return offerStatus;
	}

	/**
	 * @param offerDate the offerDate to set
	 */
	public void setOfferDate(Date offerDate) {
		this.offerDate = offerDate;
	}

	/**
	 * @return the offerDate
	 */
	public Date getOfferDate() {
		return offerDate;
	}

	/**
	 * @param offerRequest the offerRequest to set
	 */
	public void setOfferRequest(OfferRequest offerRequest) {
		this.offerRequest = offerRequest;
	}

	/**
	 * @return the offerRequest
	 */
	public OfferRequest getOfferRequest() {
		return offerRequest;
	}

	/**
	 * @param offerLines the offerLines to set
	 */
	public void setOfferLines(List<OfferLine> offerLines) {
		this.offerLines = offerLines;
	}

	/**
	 * @param offerRequestLineFixServices the offerRequestLineFixServices to set
	 */
	public void addOfferLine(OfferLine offerLine) {
		offerLines.add(offerLine);
		
		if (offerLine.getOffer() != this)
			offerLine.setOffer(this);
		
	}
	
	/**
	 * @return the offerLines
	 */
	public List<OfferLine> getOfferLines() {		
		return Collections.unmodifiableList(offerLines);
		
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
	 * @param invoices the invoices to set
	 */
	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	/**
	 * @return the invoices
	 */
	public List<Invoice> getInvoices() {
		return invoices;
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
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((offerId == null) ? 0 : offerId.hashCode());
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
		if (!(obj instanceof Offer))
			return false;
		Offer other = (Offer) obj;
		if (offerId == null) {
			if (other.offerId != null)
				return false;
		} else if (!offerId.equals(other.offerId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Offer [offerId=" + offerId + ", code=" + code + "]";
	}
	
}
