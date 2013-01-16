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
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.thingtrack.konekti.domain.Workshop;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="GAS_STATION")
public class GasStation implements Serializable {
	@Id
	@Column(name="GAS_STATION_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer gasStationId;
	
	@Column(name="CODE", nullable=false, unique=true, length=64)
	private String code;
	
	@Column(name="DESCRIPTION", length=512)
	private String description;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="GAS_STATION_TYPE_ID", nullable=false)
	private GasStationType gasStationType;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="WORKSHOP_ID")	
	private Workshop workshop;
	
	@Column(name="VOLUME", length = 10, precision = 2)
	private double volume;	
	
	@OneToMany(mappedBy="gasStation")
	private Collection<GasStationRegister> gasStationRegisters = new ArrayList<GasStationRegister>();
	
	@Column(name="COMMENT", length=512)
	private String comment;

	@ManyToOne(optional=false)
	@JoinColumn(name="GAS_STATION_STATUS_ID", nullable=false)	
	private GasStationStatus gasStationStatus;

	public enum STATUS {        
		OPENED,
		CLOSED
    }
	
	public GasStation() {
		
	}
	
	public GasStation(String code, GasStationType gasStationType, GasStationStatus gasStationStatus) {
		this.code = code;
		this.gasStationType = gasStationType;
		this.gasStationStatus = gasStationStatus;
		
	}
	
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
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
	 * @param gasStationRegisters the gasStationRegisters to set
	 */
	public void setGasStationRegisters(Collection<GasStationRegister> gasStationRegisters) {
		this.gasStationRegisters = gasStationRegisters;
	}

	/**
	 * @return the gasStationRegisters
	 */
	public Collection<GasStationRegister> getGasStationRegisters() {
		return gasStationRegisters;
	}

	/**
	 * @param gasStationId the gasStationId to set
	 */
	public void setGasStationId(Integer gasStationId) {
		this.gasStationId = gasStationId;
	}

	/**
	 * @return the gasStationId
	 */
	public Integer getGasStationId() {
		return gasStationId;
	}

	/**
	 * @param gasStationType the gasStationType to set
	 */
	public void setGasStationType(GasStationType gasStationType) {
		this.gasStationType = gasStationType;
	}

	/**
	 * @return the gasStationType
	 */
	public GasStationType getGasStationType() {
		return gasStationType;
	}

	/**
	 * @param gasStationStatus the gasStationStatus to set
	 */
	public void setGasStationStatus(GasStationStatus gasStationStatus) {
		this.gasStationStatus = gasStationStatus;
	}

	/**
	 * @return the gasStationStatus
	 */
	public GasStationStatus getGasStationStatus() {
		return gasStationStatus;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param workshop the workshop to set
	 */
	public void setWorkshop(Workshop workshop) {
		this.workshop = workshop;
	}

	/**
	 * @return the workshop
	 */
	public Workshop getWorkshop() {
		return workshop;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result
				+ ((gasStationId == null) ? 0 : gasStationId.hashCode());
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
		if (!(obj instanceof GasStation))
			return false;
		GasStation other = (GasStation) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (gasStationId == null) {
			if (other.gasStationId != null)
				return false;
		} else if (!gasStationId.equals(other.gasStationId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GasStation [gasStationId=" + gasStationId + ", code=" + code
				+ "]";
	}
	
}
