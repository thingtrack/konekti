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
@Table(name="OFFER_LINE")
public class OfferLine implements Serializable {
	@Id
	@Column(name="OFFER_LINE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer offerLineId;

	@Column(name="NUMBER", nullable=false)
	private Integer number;
	
	@ManyToOne
	@JoinColumn(name="OFFER_ID", nullable=false)
	private Offer offer;
	
	@OneToMany(mappedBy="offerLine")	
	private List<Service> services = new ArrayList<Service>();
	
	@Column(name="PRICE", length = 10, precision = 2)
	private double price;
	
	@Column(name="DISCOUNT", length = 5, precision = 2)
	private double discount;
		
	@Column(name="COMMENT")
	private String comment;
	
	@ManyToOne
	@JoinColumn(name="OFFER_LINE_STATUS_ID", nullable=false)
	private OfferLineStatus offerLineStatus;
	
	@Column(name="OFFER_LINE_DATE", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date offerLineDate;

	public enum STATUS {        
        OPENED,
        SENT,
        ACCEPTED,
        REJECTED,
        CLOSED
    }
	
	/**
	 * @return the offerLineId
	 */
	public Integer getOfferLineId() {
		return offerLineId;
	}

	/**
	 * @param offerLineId the offerLineId to set
	 */
	public void setOfferLineId(Integer offerLineId) {
		this.offerLineId = offerLineId;
	}

	/**
	 * @return the number
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * @return the services
	 */
	public List<Service> getServices() {
		return Collections.unmodifiableList(services);
		
	}

	public void addService(Service service) {
		services.add(service);
		
		if (service.getOfferLine() != this)
			service.setOfferLine(this);
		
	}
	
	/**
	 * @return the offerLineStatus
	 */
	public OfferLineStatus getOfferLineStatus() {
		return offerLineStatus;
	}

	/**
	 * @param offerLineStatus the offerLineStatus to set
	 */
	public void setOfferLineStatus(OfferLineStatus offerLineStatus) {
		this.offerLineStatus = offerLineStatus;
	}

	/**
	 * @return the offerLineDate
	 */
	public Date getOfferLineDate() {
		return offerLineDate;
	}

	/**
	 * @param offerLineDate the offerLineDate to set
	 */
	public void setOfferLineDate(Date offerLineDate) {
		this.offerLineDate = offerLineDate;
	}

	/**
	 * @param offer the offer to set
	 */
	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	/**
	 * @return the offer
	 */
	public Offer getOffer() {
		return offer;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	/**
	 * @return the discount
	 */
	public double getDiscount() {
		return discount;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result
				+ ((offerLineId == null) ? 0 : offerLineId.hashCode());
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
		if (!(obj instanceof OfferLine))
			return false;
		OfferLine other = (OfferLine) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (offerLineId == null) {
			if (other.offerLineId != null)
				return false;
		} else if (!offerLineId.equals(other.offerLineId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OfferLine [offerLineId=" + offerLineId + ", number=" + number
				+ ", offer=" + offer + ", price="
				+ price + ", discount=" + discount + ", comment=" + comment
				+ ", offerLineStatus=" + offerLineStatus + ", offerLineDate="
				+ offerLineDate + "]";
	}
}
