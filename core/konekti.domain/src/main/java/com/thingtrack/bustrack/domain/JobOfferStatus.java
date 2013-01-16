package com.thingtrack.bustrack.domain;

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
@Table(name="JOB_OFFER_STATUS")
public class JobOfferStatus implements Serializable {
	@Id
	@Column(name="JOB_OFFER_STATUS_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer jobOfferStatusId;
	
	@Column(name="CODE", nullable=false, unique=true, length=256)
	@Size(min=1, max=256)
	@NotNull
	private String code;
	
	@Column(name="DESCRIPTION", length=1024)
	@Size(min=1, max=1024)
	private String description;

	/**
	 * @param jobOfferStatusId the jobOfferStatusId to set
	 */
	public void setJobOfferStatusId(Integer jobOfferStatusId) {
		this.jobOfferStatusId = jobOfferStatusId;
	}

	/**
	 * @return the jobOfferStatusId
	 */
	public Integer getJobOfferStatusId() {
		return jobOfferStatusId;
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
				+ ((jobOfferStatusId == null) ? 0 : jobOfferStatusId.hashCode());
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
		if (!(obj instanceof JobOfferStatus))
			return false;
		JobOfferStatus other = (JobOfferStatus) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (jobOfferStatusId == null) {
			if (other.jobOfferStatusId != null)
				return false;
		} else if (!jobOfferStatusId.equals(other.jobOfferStatusId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JobOfferStatus [jobOfferStatusId=" + jobOfferStatusId
				+ ", code=" + code + ", description=" + description + "]";
	}
}
