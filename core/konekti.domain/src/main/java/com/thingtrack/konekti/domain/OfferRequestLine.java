package com.thingtrack.konekti.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="OFFER_REQUEST_LINE")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="OFFER_REQUEST_SERVICE_TYPE")
public class OfferRequestLine implements Serializable {
	@Id
	@Column(name="OFFER_REQUEST_LINE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer offerRequestLineId;
	
	@Column(name="NUMBER", nullable=false)
	private Integer number;
	
	@ManyToOne
	@JoinColumn(name="OFFER_REQUEST_ID", nullable=false)
	private OfferRequest offerRequest;

	@ManyToOne
	@JoinColumn(name="SERVICE_TYPE_ID", nullable=false)
	private ServiceType serviceType;
	
	@ManyToOne
	@JoinColumn(name="DRIVER_ID")
	private EmployeeAgent driver;
	
	@Column(name="PAYMENT")
	private String payment;
	
	@Column(name="COMMENT", length=512)
	private String comment;	

	@ManyToOne
	@JoinColumn(name="OFFER_REQUEST_LINE_STATUS_ID", nullable=false)
	private OfferRequestLineStatus  offerRequestLineStatus;
	
	@Column(name="OFFER_REQUEST_LINE_DATE", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date offerRequestLineDate=new Date();

	public enum STATUS {        
        OPENED,
        PENDING,
        TRANSFERRED,
        REJECTED,
        CLOSED
    }
	
	/**
	 * @return the offerRequestLineId
	 */
	public Integer getOfferRequestLineId() {
		return offerRequestLineId;
	}

	/**
	 * @param offerRequestLineId the offerRequestLineId to set
	 */
	public void setOfferRequestLineId(Integer offerRequestLineId) {
		this.offerRequestLineId = offerRequestLineId;
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
	 * @return the offerRequest
	 */
	public OfferRequest getOfferRequest() {
		return offerRequest;
	}

	/**
	 * @param offerRequest the offerRequest to set
	 */
	public void setOfferRequest(OfferRequest offerRequest) {
		this.offerRequest = offerRequest;
	}

	/**
	 * @return the serviceType
	 */
	public ServiceType getServiceType() {
		return serviceType;
	}

	/**
	 * @param serviceType the serviceType to set
	 */
	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the offerRequestLineStatus
	 */
	public OfferRequestLineStatus getOfferRequestLineStatus() {
		return offerRequestLineStatus;
	}

	/**
	 * @param offerRequestLineStatus the offerRequestLineStatus to set
	 */
	public void setOfferRequestLineStatus(
			OfferRequestLineStatus offerRequestLineStatus) {
		this.offerRequestLineStatus = offerRequestLineStatus;
	}

	/**
	 * @return the offerRequestLineDate
	 */
	public Date getOfferRequestLineDate() {
		return offerRequestLineDate;
	}

	/**
	 * @param offerRequestLineDate the offerRequestLineDate to set
	 */
	public void setOfferRequestLineDate(Date offerRequestLineDate) {
		this.offerRequestLineDate = offerRequestLineDate;
	}

	/**
	 * @return the driver
	 */
	public EmployeeAgent getDriver() {
		return driver;
	}

	/**
	 * @param driver the driver to set
	 */
	public void setDriver(EmployeeAgent driver) {
		this.driver = driver;
	}

	/**
	 * @return the payment
	 */
	public String getPayment() {
		return payment;
	}

	/**
	 * @param payment the payment to set
	 */
	public void setPayment(String payment) {
		this.payment = payment;
	}

}
