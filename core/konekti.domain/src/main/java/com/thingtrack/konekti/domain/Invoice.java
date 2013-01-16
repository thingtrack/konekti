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
import java.util.List;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name="INVOICE")
public class Invoice implements Serializable {
	@Id
	@Column(name="INVOICE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer invoiceId;
	
	@ManyToOne
	@JoinColumn(name="ORGANIZATION_ID", nullable=false)
	private Organization organization;
	
	@Column(name="CODE", nullable=false, unique=true, length=64)
	private String code;
	
	@ManyToOne
	@JoinColumn(name="INVOICE_TYPE_ID", nullable=false)
	private InvoiceType invoiceType;
	
	@ManyToOne
	@JoinColumn(name="CLIENT_ID", nullable=false)
	private Client invoiceClient;
	
	@Column(name="PRICE_REAL", length = 10, precision = 2)
	private double priceReal;
	
	@Column(name="PRICE_GAP", length = 10, precision = 2)
	private double priceGap;
	
	@Column(name="PRICE", length = 10, precision = 2)
	private double price;
	
	@Column(name="DISCOUNT", length = 5, precision = 2)
	private double discount;
	
	@OneToMany(mappedBy="invoice")
	private List<InvoiceLine> invoiceLines = new ArrayList<InvoiceLine>();
	
	@ManyToMany(mappedBy="invoices")
	private List<Offer> offers = new ArrayList<Offer>();
	
	@OneToMany(mappedBy="invoice")
	private List<Feedback> feedbacks = new ArrayList<Feedback>();
	
	@Column(name="OBSERVATION", length=512)
	private String observation;
	
	@ManyToOne
	@JoinColumn(name="INVOICE_STATUS_ID", nullable=false)
	private InvoiceStatus invoiceStatus;
	
	@Column(name="INVOICE_DATE", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date InvoiceDate = new Date();

	public enum STATUS {        
        OPENED,
        SENT,
        CLOSED
    }
	
	public enum TYPE {
		OTHER
	}
	/**
	 * @param invoiceId the invoiceId to set
	 */
	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	/**
	 * @return the invoiceId
	 */
	public Integer getInvoiceId() {
		return invoiceId;
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
	 * @param invoiceType the invoiceType to set
	 */
	public void setInvoiceType(InvoiceType invoiceType) {
		this.invoiceType = invoiceType;
	}

	/**
	 * @return the invoiceType
	 */
	public InvoiceType getInvoiceType() {
		return invoiceType;
	}

	/**
	 * @param feedbacks the feedbacks to set
	 */
	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	/**
	 * @return the feedbacks
	 */
	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	/**
	 * @param invoiceStatus the invoiceStatus to set
	 */
	public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	/**
	 * @return the invoiceStatus
	 */
	public InvoiceStatus getInvoiceStatus() {
		return invoiceStatus;
	}

	/**
	 * @param invoiceDate the invoiceDate to set
	 */
	public void setInvoiceDate(Date invoiceDate) {
		InvoiceDate = invoiceDate;
	}

	/**
	 * @return the invoiceDate
	 */
	public Date getInvoiceDate() {
		return InvoiceDate;
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
	 * @param invoiceClient the invoiceClient to set
	 */
	public void setInvoiceClient(Client invoiceClient) {
		this.invoiceClient = invoiceClient;
	}

	/**
	 * @return the invoiceClient
	 */
	public Client getInvoiceClient() {
		return invoiceClient;
	}

	/**
	 * @return the priceReal
	 */
	public double getPriceReal() {
		return priceReal;
	}

	/**
	 * @param priceReal the priceReal to set
	 */
	public void setPriceReal(float priceReal) {
		this.priceReal = priceReal;
	}

	/**
	 * @return the priceGap
	 */
	public double getPriceGap() {
		return priceGap;
	}

	/**
	 * @param priceGap the priceGap to set
	 */
	public void setPriceGap(double priceGap) {
		this.priceGap = priceGap;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the discount
	 */
	public double getDiscount() {
		return discount;
	}

	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(float discount) {
		this.discount = discount;
	}

	/**
	 * @return the invoiceLines
	 */
	public List<InvoiceLine> getInvoiceLines() {
		return invoiceLines;
	}

	/**
	 * @param invoiceLines the invoiceLines to set
	 */
	public void setInvoiceLines(List<InvoiceLine> invoiceLines) {
		this.invoiceLines = invoiceLines;
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
		result = prime * result
				+ ((InvoiceDate == null) ? 0 : InvoiceDate.hashCode());
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
		if (!(obj instanceof Invoice))
			return false;
		Invoice other = (Invoice) obj;
		if (InvoiceDate == null) {
			if (other.InvoiceDate != null)
				return false;
		} else if (!InvoiceDate.equals(other.InvoiceDate))
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
		return "Invoice [invoiceId=" + invoiceId + ", organization="
				+ organization + ", code=" + code + ", invoiceType="
				+ invoiceType + ", invoiceClient=" + invoiceClient
				+ ", priceReal=" + priceReal + ", priceGap=" + priceGap
				+ ", price=" + price + ", discount=" + discount
				+ ", invoiceLines=" + invoiceLines + ", offers=" + offers
				+ ", feedbacks=" + feedbacks + ", observation=" + observation
				+ ", invoiceStatus=" + invoiceStatus + ", InvoiceDate="
				+ InvoiceDate + "]";
	}
}
