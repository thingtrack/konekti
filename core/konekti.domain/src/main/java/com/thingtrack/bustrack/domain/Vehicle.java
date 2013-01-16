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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.thingtrack.konekti.domain.EmployeeAgent;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.sensor.SensorTelemetry;
import com.thingtrack.konekti.domain.Supplier;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="VEHICLE")
public class Vehicle implements Serializable {
	@Id
	@Column(name="VEHICLE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer vehicleId;
	
	@ManyToMany
	@JoinTable(name="VEHICLE_ORGANIZATION",
			   joinColumns=@JoinColumn(name="VEHICLE_ID"),
			   inverseJoinColumns=@JoinColumn(name="ORGANIZATION_ID"))	
	private List<Organization> organizations = new ArrayList<Organization>();
	
	@ManyToOne
	@JoinColumn(name="VEHICLE_TYPE_ID", nullable=false)	
	private VehicleType vehicleType;
	
	@Column(name="VEHICLE_NUMBER", nullable=false, unique=true)
	private String vehicleNumber;

	@ManyToOne
	@JoinColumn(name="SUPPLIER_ID")	
	private Supplier supplier;
	
	@Column(name="ENROLLMENT", nullable=false, length=64)
	private String enrollment;
	
	@OneToMany(mappedBy="vehicle")
	private List<Route> routes;
		
	@ManyToMany
	@JoinTable(name="VEHICLE_DRIVER",
			   joinColumns=@JoinColumn(name="VEHICLE_ID"),
			   inverseJoinColumns=@JoinColumn(name="DRIVER_ID"))	
	private List<EmployeeAgent> drivers = new ArrayList<EmployeeAgent>();
	
	@Column(name="CHASSIS", length=64)
	private String chassis;
	
	@ManyToOne
	@JoinColumn(name="HABITUAL_DRIVER__ID")	
	private EmployeeAgent habitualDriver;
	
	@Column(name="FRAME_NUMBER", length=64)
	private String frameNumber;
	
	@Column(name="SEATINGS", nullable=false)
	private Integer seatings;

	@Column(name="LENGTH", nullable=false)
	private float length;
	
	@Column(name="PURCHASE_DATE")
	@Temporal(TemporalType.DATE)
	private Date purchaseDate;
	
	@Column(name="REGISTRATION_DATE")
	@Temporal(TemporalType.DATE)
	private Date registrationDate;

	@Column(name="ITV_DATE")
	@Temporal(TemporalType.DATE)
	private Date ITVDate;

	@Column(name="INSURANCE_DATE")
	@Temporal(TemporalType.DATE)
	private Date insuranceDate;
	
	@Column(name="ADAPTED", nullable=false)
	private boolean adapted=false;
	
	@Column(name="DVD", nullable=false)
	private boolean dvd=false;
	
	@Column(name="FRIDGE", nullable=false)
	private boolean fridge=false;
	
	@Column(name="BELTS", nullable=false)
	private boolean belts=false;
	
	@Column(name=" MICROPHONE", nullable=false)
	private boolean microphone=false;
	
	@Column(name="VIDEO", nullable=false)
	private boolean video=false;
	
	@OneToMany(mappedBy="vehicle", orphanRemoval=true)
	private List<Tire> tires = new ArrayList<Tire>();
		
	@Column(name="OBSERVATION")
	private String observation;

	@ManyToOne
	@JoinColumn(name="VEHICLE_STATUS_ID", nullable=false)
	private VehicleStatus vehicleStatus;

	@OneToOne
	@JoinColumn(name="SENSOR_TELEMETRY_ID")
	private SensorTelemetry sensorTelemetry;
	
	/**
	 * @param vehicleId the vehicleId to set
	 */
	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	/**
	 * @return the vehicleId
	 */
	public Integer getVehicleId() {
		return vehicleId;
	}

	/**
	 * @param vehicleType the vehicleType to set
	 */
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	/**
	 * @return the vehicleType
	 */
	public VehicleType getVehicleType() {
		return vehicleType;
	}

	/**
	 * @param vehicleNumber the vehicleNumber to set
	 */
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	/**
	 * @return the vehicleNumber
	 */
	public String getVehicleNumber() {
		return vehicleNumber;
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
	 * @param enrollment the enrollment to set
	 */
	public void setEnrollment(String enrollment) {
		this.enrollment = enrollment;
	}

	/**
	 * @return the enrollment
	 */
	public String getEnrollment() {
		return enrollment;
	}

	/**
	 * @param chassis the chassis to set
	 */
	public void setChassis(String chassis) {
		this.chassis = chassis;
	}

	/**
	 * @return the chassis
	 */
	public String getChassis() {
		return chassis;
	}

	/**
	 * @param frameNumber the frameNumber to set
	 */
	public void setFrameNumber(String frameNumber) {
		this.frameNumber = frameNumber;
	}

	/**
	 * @return the frameNumber
	 */
	public String getFrameNumber() {
		return frameNumber;
	}

	/**
	 * @param seatings the seatings to set
	 */
	public void setSeatings(Integer seatings) {
		this.seatings = seatings;
	}

	/**
	 * @return the seatings
	 */
	public Integer getSeatings() {
		return seatings;
	}

	/**
	 * @param purchaseDate the purchaseDate to set
	 */
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	/**
	 * @return the purchaseDate
	 */
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	/**
	 * @param registrationDate the registrationDate to set
	 */
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	/**
	 * @return the registrationDate
	 */
	public Date getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * @param adapted the adapted to set
	 */
	public void setAdapted(Boolean adapted) {
		this.adapted = adapted;
	}

	/**
	 * @return the adapted
	 */
	public Boolean getAdapted() {
		return adapted;
	}

	/**
	 * @param dvd the dvd to set
	 */
	public void setDvd(Boolean dvd) {
		this.dvd = dvd;
	}

	/**
	 * @return the dvd
	 */
	public Boolean getDvd() {
		return dvd;
	}

	/**
	 * @param fridge the fridge to set
	 */
	public void setFridge(Boolean fridge) {
		this.fridge = fridge;
	}

	/**
	 * @return the fridge
	 */
	public Boolean getFridge() {
		return fridge;
	}

	/**
	 * @param belts the belts to set
	 */
	public void setBelts(Boolean belts) {
		this.belts = belts;
	}

	/**
	 * @return the belts
	 */
	public Boolean getBelts() {
		return belts;
	}

	/**
	 * @param microphone the microphone to set
	 */
	public void setMicrophone(Boolean microphone) {
		this.microphone = microphone;
	}

	/**
	 * @return the microphone
	 */
	public Boolean getMicrophone() {
		return microphone;
	}

	/**
	 * @param video the video to set
	 */
	public void setVideo(Boolean video) {
		this.video = video;
	}

	/**
	 * @return the video
	 */
	public Boolean getVideo() {
		return video;
	}

	/**
	 * @param tires the tires to set
	 */
	public void setTires(List<Tire> tires) {
		this.tires = tires;
	}

	/**
	 * @return the tires
	 */
	public List<Tire> getTires() {
		return tires;
	}

	/**
	 * @param organizations the organizations to set
	 */
	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}

	/**
	 * @return the organizations
	 */
	public List<Organization> getOrganizations() {
		return organizations;
	}

	/**
	 * @param vehicleStatus the vehicleStatus to set
	 */
	public void setVehicleStatus(VehicleStatus vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}

	/**
	 * @return the vehicleStatus
	 */
	public VehicleStatus getVehicleStatus() {
		return vehicleStatus;
	}

	/**
	 * @param observation the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}

	/**
	 * @return the observation
	 */
	public String getObservation() {
		return observation;
	}

	/**
	 * @param routes the routes to set
	 */
	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}

	/**
	 * @return the routes
	 */
	public List<Route> getRoutes() {
		return routes;
	}

	/**
	 * @param drivers the drivers to set
	 */
	public void setDrivers(List<EmployeeAgent> drivers) {
		this.drivers = drivers;
	}

	/**
	 * @return the drivers
	 */
	public List<EmployeeAgent> getDrivers() {
		return drivers;
	}

	/**
	 * @param habitualDriver the habitualDriver to set
	 */
	public void setHabitualDriver(EmployeeAgent habitualDriver) {
		this.habitualDriver = habitualDriver;
	}

	/**
	 * @return the habitualDriver
	 */
	public EmployeeAgent getHabitualDriver() {
		return habitualDriver;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((vehicleId == null) ? 0 : vehicleId.hashCode());
		result = prime * result
				+ ((vehicleNumber == null) ? 0 : vehicleNumber.hashCode());
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
		if (!(obj instanceof Vehicle))
			return false;
		Vehicle other = (Vehicle) obj;
		if (vehicleId == null) {
			if (other.vehicleId != null)
				return false;
		} else if (!vehicleId.equals(other.vehicleId))
			return false;
		if (vehicleNumber == null) {
			if (other.vehicleNumber != null)
				return false;
		} else if (!vehicleNumber.equals(other.vehicleNumber))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
//	@Override
//	public String toString() {
//		return "Vehicle [vehicleId=" + vehicleId + ", organizations="
//				+ organizations + ", vehicleType=" + vehicleType
//				+ ", vehicleNumber=" + vehicleNumber + ", supplier=" + supplier
//				+ ", enrollment=" + enrollment + ", routes=" + routes
//				+ ", drivers=" + drivers + ", chassis=" + chassis
//				+ ", habitualDriver=" + habitualDriver + ", frameNumber="
//				+ frameNumber + ", seatings=" + seatings + ", purchaseDate="
//				+ purchaseDate + ", registrationDate=" + registrationDate
//				+ ", adapted=" + adapted + ", dvd=" + dvd + ", fridge="
//				+ fridge + ", belts=" + belts + ", microphone=" + microphone
//				+ ", video=" + video + ", tires=" + tires + ", observation="
//				+ observation + ", vehicleStatus=" + vehicleStatus + "]";
//	}

	/**
	 * @param sensorTelemetry the sensorTelemetry to set
	 */
	public void setSensorTelemetry(SensorTelemetry sensorTelemetry) {
		this.sensorTelemetry = sensorTelemetry;
	}

	/**
	 * @return the sensorTelemetry
	 */
	public SensorTelemetry getSensorTelemetry() {
		return sensorTelemetry;
	}

	/**
	 * @return the length
	 */
	public float getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(float length) {
		this.length = length;
	}

	/**
	 * @return the iTVDate
	 */
	public Date getITVDate() {
		return ITVDate;
	}

	/**
	 * @param iTVDate the iTVDate to set
	 */
	public void setITVDate(Date iTVDate) {
		ITVDate = iTVDate;
	}

	/**
	 * @return the insuranceDate
	 */
	public Date getInsuranceDate() {
		return insuranceDate;
	}

	/**
	 * @param insuranceDate the insuranceDate to set
	 */
	public void setInsuranceDate(Date insuranceDate) {
		this.insuranceDate = insuranceDate;
	}
	
}
