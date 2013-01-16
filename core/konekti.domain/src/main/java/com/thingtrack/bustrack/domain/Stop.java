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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.thingtrack.konekti.domain.Address;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="STOP")
public class Stop implements Serializable {
	@Id
	@Column(name="STOP_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer stopId;
	
	@ManyToMany(mappedBy="stops")
	private List<Route> routes = new ArrayList<Route>();
	
	@OneToOne
	@JoinColumn(name="STOP_ADDRESS_ID", nullable=false)
	private Address stopAddress;
	
	@Column(name="STOP_ARRIVAL_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date stopArrivalDate;
	
	@Column(name="STOP_CHECKOUT_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date stopCheckoutDate;
	
	@Column(name="COMMENT", length=512)
	private String comment;
	
	@Column(name="ACTIVE", nullable=false)
	private boolean active=true;

	public Stop() {}
	
	public Stop(Address stopAddress) {
		this.stopAddress = stopAddress;
	}
	
	public Stop(Address stopAddress, boolean active) {
		this.stopAddress = stopAddress;
		this.active = active;
	}
	
	/**
	 * @return the stopId
	 */
	public Integer getStopId() {
		return stopId;
	}

	/**
	 * @param stopId the stopId to set
	 */
	public void setStopId(Integer stopId) {
		this.stopId = stopId;
	}

	/**
	 * @return the routes
	 */
	public List<Route> getRoutes() {
		return Collections.unmodifiableList(routes);
	}

	public void addRoute(Route route) throws Exception {
		routes.add(route);
		
		if (!route.getStops().contains(this))
			route.addStop(this);
		
	}
	
	/**
	 * @return the stopAddress
	 */
	public Address getStopAddress() {
		return stopAddress;
	}

	/**
	 * @param stopAddress the stopAddress to set
	 */
	public void setStopAddress(Address stopAddress) {
		this.stopAddress = stopAddress;
	}

	/**
	 * @return the stopArrivalDate
	 */
	public Date getStopArrivalDate() {
		return stopArrivalDate;
	}

	/**
	 * @param stopArrivalDate the stopArrivalDate to set
	 */
	public void setStopArrivalDate(Date stopArrivalDate) {
		this.stopArrivalDate = stopArrivalDate;
	}

	/**
	 * @return the stopCheckoutDate
	 */
	public Date getStopCheckoutDate() {
		return stopCheckoutDate;
	}

	/**
	 * @param stopCheckoutDate the stopCheckoutDate to set
	 */
	public void setStopCheckoutDate(Date stopCheckoutDate) {
		this.stopCheckoutDate = stopCheckoutDate;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
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
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stopId == null) ? 0 : stopId.hashCode());
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
		if (!(obj instanceof Stop))
			return false;
		Stop other = (Stop) obj;
		if (stopId == null) {
			if (other.stopId != null)
				return false;
		} else if (!stopId.equals(other.stopId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Stop [stopId=" + stopId + "]";
	}
	
}
