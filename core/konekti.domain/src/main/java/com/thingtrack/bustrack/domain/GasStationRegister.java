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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="GAS_STATION_REGISTER")
public class GasStationRegister implements Serializable {
	@Id
	@Column(name="GAS_STATION_REGISTER_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer gasStationRegisterId;
	
	@ManyToOne
	@JoinColumn(name="GAS_TYPE_ID", nullable=false)
	private GasType gasType;
	
	@Column(name="VOLUME", nullable=false, length = 10, precision = 2)
	private double volume;
	
	@ManyToOne
	@JoinColumn(name="GAS_STATION_ID", nullable=false)	
	private GasStation gasStation;
	
	@Column(name="PRICE", length = 10, precision = 2)
	private double price;
	
	@Column(name="COMMENT", length=512)
	private String comment;
	
	@Column(name="GAS_STATION_REGISTER_DATE", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date gasStationRegisterDate=new Date();

	/**
	 * @param gasType the gasType to set
	 */
	public void setGasType(GasType gasType) {
		this.gasType = gasType;
	}

	/**
	 * @return the gasType
	 */
	public GasType getGasType() {
		return gasType;
	}

	/**
	 * @param volume the volume to set
	 */
	public void setVolume(double volume) {
		this.volume = volume;
	}

	/**
	 * @return the volume
	 */
	public double getVolume() {
		return volume;
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
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param gasStation the gasStation to set
	 */
	public void setGasStation(GasStation gasStation) {
		this.gasStation = gasStation;
	}

	/**
	 * @return the gasStation
	 */
	public GasStation getGasStation() {
		return gasStation;
	}

	/**
	 * @param gasStationRegisterId the gasStationRegisterId to set
	 */
	public void setGasStationRegisterId(Integer gasStationRegisterId) {
		this.gasStationRegisterId = gasStationRegisterId;
	}

	/**
	 * @return the gasStationRegisterId
	 */
	public Integer getGasStationRegisterId() {
		return gasStationRegisterId;
	}

	/**
	 * @param gasStationRegisterDate the gasStationRegisterDate to set
	 */
	public void setGasStationRegisterDate(Date gasStationRegisterDate) {
		this.gasStationRegisterDate = gasStationRegisterDate;
	}

	/**
	 * @return the gasStationRegisterDate
	 */
	public Date getGasStationRegisterDate() {
		return gasStationRegisterDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((gasStationRegisterId == null) ? 0 : gasStationRegisterId
						.hashCode());
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
		if (!(obj instanceof GasStationRegister))
			return false;
		GasStationRegister other = (GasStationRegister) obj;
		if (gasStationRegisterId == null) {
			if (other.gasStationRegisterId != null)
				return false;
		} else if (!gasStationRegisterId.equals(other.gasStationRegisterId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GasStationRegister [gasStationRegisterId="
				+ gasStationRegisterId + ", gasType=" + gasType + ", volume="
				+ volume + ", gasStation=" + gasStation + ", price=" + price
				+ ", comment=" + comment + ", gasStationRegisterDate="
				+ gasStationRegisterDate + "]";
	}
}
