package com.thingtrack.konekti.domain.sensor;

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
@Table(name="SENSOR_TYPE")
public class SensorType {
	@Id
	@Column(name="SENSOR_TYPE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer sensorTypeId;
	
	@Column(name="CODE", nullable=false, unique=true, length=64)
	private String code;
	
	@Column(name="DESCRIPTION", length=512)
	private String description;

	/**
	 * @return the sensorTypeId
	 */
	public Integer getSensorTypeId() {
		return sensorTypeId;
	}

	/**
	 * @param sensorTypeId the sensorTypeId to set
	 */
	public void setSensorTypeId(Integer sensorTypeId) {
		this.sensorTypeId = sensorTypeId;
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
				+ ((sensorTypeId == null) ? 0 : sensorTypeId.hashCode());
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
		if (!(obj instanceof SensorType))
			return false;
		SensorType other = (SensorType) obj;
		if (sensorTypeId == null) {
			if (other.sensorTypeId != null)
				return false;
		} else if (!sensorTypeId.equals(other.sensorTypeId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SensorType [sensorTypeId=" + sensorTypeId + ", code=" + code
				+ ", description=" + description + "]";
	}
}
