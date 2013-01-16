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
