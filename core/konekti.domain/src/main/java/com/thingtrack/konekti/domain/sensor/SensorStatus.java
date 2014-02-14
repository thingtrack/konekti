package com.thingtrack.konekti.domain.sensor;

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
@Entity
@Table(name="SENSOR_STATUS")
public class SensorStatus {
	@Id
	@Column(name="SENSOR_STATUS_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer sensorStatusId;
	
	@Column(name="CODE", nullable=false, unique=true, length=64)
	private String code;
	
	@Column(name="DESCRIPTION", length=512)
	private String description;
	
	/**
	 * @return the sensorStatusId
	 */
	public Integer getSensorStatusId() {
		return sensorStatusId;
	}

	/**
	 * @param sensorStatusId the sensorStatusId to set
	 */
	public void setSensorStatusId(Integer sensorStatusId) {
		this.sensorStatusId = sensorStatusId;
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
				+ ((sensorStatusId == null) ? 0 : sensorStatusId.hashCode());
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
		if (!(obj instanceof SensorStatus))
			return false;
		SensorStatus other = (SensorStatus) obj;
		if (sensorStatusId == null) {
			if (other.sensorStatusId != null)
				return false;
		} else if (!sensorStatusId.equals(other.sensorStatusId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SensorStatus [sensorStatusId=" + sensorStatusId + ", code="
				+ code + ", description=" + description + "]";
	}
}
