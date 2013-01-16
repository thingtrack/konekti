package com.thingtrack.konekti.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.thingtrack.bustrack.domain.GasStation;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="WORKSHOP")
public class Workshop implements Serializable {
	@Id
	@Column(name="WORKSHOP_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer workshopId;

	@Column(name="CODE", nullable=false, unique=true, length=64)
	private String code;

	@Column(name="NAME", length=64)
	private String name;

	@Column(name="DESCRIPTION", length=512)
	private String description;

	@ManyToOne
	@JoinColumn(name="WORKSHOP_TYPE_ID", nullable=false)
	private WorkshopType workshopType;

	@ManyToOne
	@JoinColumn(name="LOCATION_ID", nullable=false)	
	private Location location;

	@OneToMany(mappedBy="workshop")	
	private List<GasStation> gasStations = new ArrayList<GasStation>();

	@Column(name="COMMENT", length=512)
	private String Comment;

	@Column(name="ACTIVE", nullable=false)
	private boolean active=true;

	public Workshop() {

	}

	public Workshop(String code, WorkshopType workshopType) {
		this(code, workshopType, true);

	}

	public Workshop(String code, WorkshopType workshopType, Boolean active) {
		this.code = code;
		this.workshopType = workshopType;
		this.active = active;

	}

	/**
	 * @return the workshopId
	 */
	public Integer getWorkshopId() {
		return workshopId;
	}

	/**
	 * @param workshopId the workshopId to set
	 */
	public void setWorkshopId(Integer workshopId) {
		this.workshopId = workshopId;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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

	/**
	 * @return the workshopType
	 */
	public WorkshopType getWorkshopType() {
		return workshopType;
	}

	/**
	 * @param workshopType the workshopType to set
	 */
	public void setWorkshopType(WorkshopType workshopType) {
		this.workshopType = workshopType;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return Comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		Comment = comment;
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

	/**
	 * @param gasStations the gasStations to set
	 */
	public void setGasStations(List<GasStation> gasStations) {
		this.gasStations = gasStations;
	}

	/**
	 * @return the gasStations
	 */
	public List<GasStation> getGasStations() {
		return gasStations;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
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
				+ ((workshopId == null) ? 0 : workshopId.hashCode());
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
		if (!(obj instanceof Workshop))
			return false;
		Workshop other = (Workshop) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (workshopId == null) {
			if (other.workshopId != null)
				return false;
		} else if (!workshopId.equals(other.workshopId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Workshop [workshopId=" + workshopId + ", code=" + code + "]";
	}

}