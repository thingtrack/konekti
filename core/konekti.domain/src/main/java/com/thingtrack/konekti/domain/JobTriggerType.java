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
@Table(name="JOB_TRIGGER_TYPE")
public class JobTriggerType implements Serializable {
	@Id
	@Column(name="JOB_TRIGGER_TYPE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer jobTriggerTypeId;
	
	@Column(name="CODE")
	private String code;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	/**
	 * @return the jobTriggerTypeId
	 */
	public Integer getJobTriggerTypeId() {
		return jobTriggerTypeId;
	}

	/**
	 * @param jobTriggerTypeId the jobTriggerTypeId to set
	 */
	public void setJobTriggerId(Integer jobTriggerTypeId) {
		this.jobTriggerTypeId = jobTriggerTypeId;
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
		result = prime * result
				+ ((jobTriggerTypeId == null) ? 0 : jobTriggerTypeId.hashCode());
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
		if (getClass() != obj.getClass())
			return false;
		JobTriggerType other = (JobTriggerType) obj;
		if (jobTriggerTypeId == null) {
			if (other.jobTriggerTypeId != null)
				return false;
		} else if (!jobTriggerTypeId.equals(other.jobTriggerTypeId))
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
		return "jobType [jobTypeId=" + jobTriggerTypeId + ", code="
				+ code + ", description=" + description + "]";
	}
}
