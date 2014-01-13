package com.thingtrack.konekti.domain;

/*
 * #%L
 * Konekti Domain Layer
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2010 - 2014 Thingtrack s.l.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity class,
 * <p>
 * Represents the types that a calendar can be
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="CALENDAR_TYPE")
public class CalendarType implements Serializable {
	
	/**
	 * Unique identifier
	 */
	@Id
	@Column(name="CALENDAR_TYPE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer calendarTypeId;
	
	
	/**
	 * Unique code, not null
	 */
	@Column(name="CODE", nullable=false, unique=true, length=64)
	private String code;
	
	/**
	 * Description
	 */
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
