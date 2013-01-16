/*
 * Copyright 2011 Thingtrack, S.L.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.thingtrack.bustrack.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.thingtrack.konekti.domain.Supplier;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="TIRE")
public class Tire implements Serializable {
	@Id
	@Column(name="TIRE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer tireId;
	
	@ManyToOne
	@JoinColumn(name="TIRE_TYPE_ID", nullable=false)
	private TireType tireType;

	@ManyToOne
	@JoinColumn(name="VEHICLE_ID")
	private Vehicle vehicle = new Vehicle();

	@ManyToOne
	@JoinColumn(name="SUPPLIER_ID")
	private Supplier supplier;
	
	@Column(name="VEHICLE_LOCATION")
	private String vehicleLocation;
	
	@Column(name="SERIAL_NUMBER", nullable=false, unique=false, length=64)
	private String serialNumber;
	
	@Column(name="KM")
	private Integer km;
	
	@Column(name="PUNCTURE")
	private Integer puncture;
	
	@Column(name="COMMENT", length=512)
	private String comment;
	
	@ManyToOne
	@JoinColumn(name="TIRE_STATUS_ID", nullable=false)	
	private TireStatus tireStatus;

	/**
	 * @param tireId the tireId to set
	 */
	public void setTireId(Integer tireId) {
		this.tireId = tireId;
	}

	/**
	 * @return the tireId
	 */
	public Integer getTireId() {
		return tireId;
	}

	/**
	 * @param tireType the tireType to set
	 */
	public void setTireType(TireType tireType) {
		this.tireType = tireType;
	}

	/**
	 * @return the tireType
	 */
	public TireType getTireType() {
		return tireType;
	}

	/**
	 * @param vehicle the vehicle to set
	 */
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	/**
	 * @return the vehicle
	 */
	public Vehicle getVehicle() {
		return vehicle;
	}

	/**
	 * @param supplier the supplier to set
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	/**
	 * @return the supplier
	 */
	public Supplier getSupplier() {
		return supplier;
	}

	/**
	 * @param vehicleLocation the vehicleLocation to set
	 */
	public void setVehicleLocation(String vehicleLocation) {
		this.vehicleLocation = vehicleLocation;
	}

	/**
	 * @return the vehicleLocation
	 */
	public String getVehicleLocation() {
		return vehicleLocation;
	}

	/**
	 * @param serialNumber the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @param km the km to set
	 */
	public void setKm(Integer km) {
		this.km = km;
	}

	/**
	 * @return the km
	 */
	public Integer getKm() {
		return km;
	}

	/**
	 * @param puncture the puncture to set
	 */
	public void setPuncture(Integer puncture) {
		this.puncture = puncture;
	}

	/**
	 * @return the puncture
	 */
	public Integer getPuncture() {
		return puncture;
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

	/**
	 * @param tireStatus the tireStatus to set
	 */
	public void setTireStatus(TireStatus tireStatus) {
		this.tireStatus = tireStatus;
	}

	/**
	 * @return the tireStatus
	 */
	public TireStatus getTireStatus() {
		return tireStatus;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((serialNumber == null) ? 0 : serialNumber.hashCode());
		result = prime * result + ((tireId == null) ? 0 : tireId.hashCode());
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
		if (!(obj instanceof Tire))
			return false;
		Tire other = (Tire) obj;
		if (serialNumber == null) {
			if (other.serialNumber != null)
				return false;
		} else if (!serialNumber.equals(other.serialNumber))
			return false;
		if (tireId == null) {
			if (other.tireId != null)
				return false;
		} else if (!tireId.equals(other.tireId))
			return false;
		return true;
	}
}
