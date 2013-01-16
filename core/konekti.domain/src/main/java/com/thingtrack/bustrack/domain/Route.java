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
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.thingtrack.konekti.domain.EmployeeAgent;
import com.thingtrack.konekti.domain.Service;

/**
 * @author Thingtrack S.L.
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "ROUTE")
public class Route implements Serializable {
	@Id
	@Column(name = "ROUTE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer routeId;

	@Column(name = "CODE", nullable = false, length = 64)
	private String code;

	@Column(name = "DESCRIPTION", length = 512)
	private String description;

	@Column(name = "KILOMETER", length = 32)
	private String kilometer;

	@ManyToMany(mappedBy = "routes")
	private List<Service> services = new ArrayList<Service>();

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@OrderBy("stopArrivalDate, stopCheckoutDate")
	@JoinTable(name = "ROUTE_STOP", joinColumns = @JoinColumn(name = "ROUTE_ID"), inverseJoinColumns = @JoinColumn(name = "STOP_ID"))
	private List<Stop> stops = new ArrayList<Stop>();

	@ManyToOne
	@JoinColumn(name = "DRIVER_ID")
	private EmployeeAgent driver;

	@ManyToOne
	@JoinColumn(name = "VEHICLE_ID")
	private Vehicle vehicle;

	@Column(name = "COMMENT", length = 512)
	private String comment;

	@Column(name = "ACTIVE", nullable = false)
	private boolean active = true;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ROUTE_STATUS_ID", nullable = false)
	private RouteStatus routeStatus;

	@Column(name = "START_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	@Column(name = "STOP_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date stopDate;

	public enum STATUS {        
		PENDING,
		RUNNING,
		FINISHED
    }
	
	public Route() {

	}

	public Route(String code, RouteStatus routeStatus) {
		this.code = code;
		this.routeStatus = routeStatus;

	}

	/**
	 * @return the routeId
	 */
	public Integer getRouteId() {
		return routeId;
	}

	/**
	 * @param routeId
	 *            the routeId to set
	 */
	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
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
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the kilometer
	 */
	public String getKilometer() {
		return kilometer;
	}

	/**
	 * @param kilometer
	 *            the kilometer to set
	 */
	public void setKilometer(String kilometer) {
		this.kilometer = kilometer;
	}

	/**
	 * @return the services
	 */
	public List<Service> getServices() {
		return Collections.unmodifiableList(services);

	}

	public void addService(Service service) {

		services.add(service);

		if (!service.getRoutes().contains(this))
			service.addRoute(this);
	}

	public void removeService(Service service) {

		services.remove(service);
		service.removeRoute(this);
	}

	/**
	 * @return the drivers
	 */
	public EmployeeAgent getDriver() {
		return driver;
	}

	/**
	 * @param drivers
	 *            the drivers to set
	 */
	public void setDriver(EmployeeAgent driver) {
		this.driver = driver;
	}

	/**
	 * @return the vehicles
	 */
	public Vehicle getVehicle() {
		return vehicle;
	}

	/**
	 * @param vehicles
	 *            the vehicles to set
	 */
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the stops
	 */
	public List<Stop> getStops() {
		return Collections.unmodifiableList(stops);
	}

	public void addStop(Stop stop) throws Exception {
		
		if (stops.size() == 2)
			throw new Exception("Only must be exist two stops inside a route!");

		stops.add(stop);

		if (!stop.getRoutes().contains(this))
			stop.addRoute(this);

	}

	/**
	 * @param routeStatus
	 *            the routeStatus to set
	 */
	public void setRouteStatus(RouteStatus routeStatus) {
		this.routeStatus = routeStatus;
	}

	/**
	 * @return the routeStatus
	 */
	public RouteStatus getRouteStatus() {
		return routeStatus;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param stopDate
	 *            the stopDate to set
	 */
	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

	/**
	 * @return the stopDate
	 */
	public Date getStopDate() {
		return stopDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((routeId == null) ? 0 : routeId.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Route))
			return false;
		Route other = (Route) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (routeId == null) {
			if (other.routeId != null)
				return false;
		} else if (!routeId.equals(other.routeId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Route [routeId=" + routeId + ", code=" + code + "]";

	}
}
