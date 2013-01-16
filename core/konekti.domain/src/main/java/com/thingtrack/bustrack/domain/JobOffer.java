package com.thingtrack.bustrack.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Thingtrack S.L.
 *
 */
@Entity
@Table(name="JOB_OFFER")
public class JobOffer {
	@Id
	@Column(name="JOB_OFFER_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer jobOfferId;
	
	@Column(name="NAME", nullable=false, length=256)
	private String name;
	
	@Column(name="SURNAME", nullable=false, length=256)
	private String surname;
	
	@Column(name="PHONE", nullable=false, length=32)
	private String phone;
	
	@Column(name="MOBILE", length=32)
	private String mobile;
	
	@Column(name="EMAIL", length=64)
	private String email;
	
	@Column(name="TITTLE", length=64)
	private String tittle;
	
	@Column(name="CV")
	@Lob
	private byte[] cv;
	
	@Column(name="OBSERVATION", length=256)
	private String observation;
	
	@ManyToOne
	@JoinColumn(name="JOB_OFFER_TYPE_ID", nullable=false)
	private JobOfferType jobOfferType;

	@ManyToOne
	@JoinColumn(name="JOB_OFFER_STATUS_ID", nullable=false)
	private JobOfferStatus jobOfferStatus;

	@Column(name="JOB_OFFER_DATE", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date jobOfferDate=new Date();
	/**
	 * @param jobOfferId the jobOfferId to set
	 */
	public void setJobOfferId(int jobOfferId) {
		this.jobOfferId = jobOfferId;
	}

	/**
	 * @return the jobOfferId
	 */
	public int getJobOfferId() {
		return jobOfferId;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param tittle the tittle to set
	 */
	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	/**
	 * @return the tittle
	 */
	public String getTittle() {
		return tittle;
	}

	/**
	 * @param cv the cv to set
	 */
	public void setCv(byte[] cv) {
		this.cv = cv;
	}

	/**
	 * @return the cv
	 */
	public byte[] getCv() {
		return cv;
	}

	/**
	 * @param observations the observations to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}

	/**
	 * @return the observations
	 */
	public String getObservation() {
		return observation;
	}

	/**
	 * @param jobOfferType the jobOfferType to set
	 */
	public void setJobOfferType(JobOfferType jobOfferType) {
		this.jobOfferType = jobOfferType;
	}

	/**
	 * @return the jobOfferType
	 */
	public JobOfferType getJobOfferType() {
		return jobOfferType;
	}

	/**
	 * @param jobOfferStatus the jobOfferStatus to set
	 */
	public void setJobOfferStatus(JobOfferStatus jobOfferStatus) {
		this.jobOfferStatus = jobOfferStatus;
	}

	/**
	 * @return the jobOfferStatus
	 */
	public JobOfferStatus getJobOfferStatus() {
		return jobOfferStatus;
	}

	/**
	 * @param jobOfferDate the jobOfferDate to set
	 */
	public void setJobOfferDate(Date jobOfferDate) {
		this.jobOfferDate = jobOfferDate;
	}

	/**
	 * @return the jobOfferDate
	 */
	public Date getJobOfferDate() {
		return jobOfferDate;
	}
	
}
