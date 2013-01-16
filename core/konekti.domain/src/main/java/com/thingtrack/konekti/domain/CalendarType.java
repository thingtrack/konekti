package com.thingtrack.konekti.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Tyhingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="CALENDAR_TYPE")
public class CalendarType implements Serializable {
	@Id
	@Column(name="CALENDAR_TYPE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer calendarTypeId;
	
	@Column(name="CODE", nullable=false, unique=true, length=64)
	private String code;
	
	@Column(name="DESCRIPTION", length=512)	
	private String description;

	/**
	 * @return the calendarTypeId
	 */
	public Integer getCalendarTypeId() {
		return calendarTypeId;
	}

	/**
	 * @param calendarTypeId the calendarTypeId to set
	 */
	public void setCalendarTypeId(Integer calendarTypeId) {
		this.calendarTypeId = calendarTypeId;
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
				+ ((calendarTypeId == null) ? 0 : calendarTypeId.hashCode());
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
		if (!(obj instanceof CalendarType))
			return false;
		CalendarType other = (CalendarType) obj;
		if (calendarTypeId == null) {
			if (other.calendarTypeId != null)
				return false;
		} else if (!calendarTypeId.equals(other.calendarTypeId))
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
		return "CalendarType [calendarTypeId=" + calendarTypeId + ", code="
				+ code + ", description=" + description + "]";
	}
}
