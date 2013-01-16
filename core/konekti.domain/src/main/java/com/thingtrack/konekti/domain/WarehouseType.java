package com.thingtrack.konekti.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="WAREHOUSE_TYPE")
public class WarehouseType implements Serializable {
	@Id
	@Column(name="WAREHOUSE_TYPE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer warehouseTypeId;
	
	@Column(name="NAME", nullable=false, unique=true, length=256)
	@Size(min=1, max=256)
	@NotNull
	private String name;
	
	@Column(name="DESCRIPTION", length=1024)
	@Size(min=1, max=1024)
	private String description;

	/**
	 * @param warehouseTypeId the warehouseTypeId to set
	 */
	public void setWarehouseTypeId(Integer warehouseTypeId) {
		this.warehouseTypeId = warehouseTypeId;
	}

	/**
	 * @return the warehouseTypeId
	 */
	public Integer getWarehouseTypeId() {
		return warehouseTypeId;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((warehouseTypeId == null) ? 0 : warehouseTypeId.hashCode());
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
		if (!(obj instanceof WarehouseType))
			return false;
		WarehouseType other = (WarehouseType) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (warehouseTypeId == null) {
			if (other.warehouseTypeId != null)
				return false;
		} else if (!warehouseTypeId.equals(other.warehouseTypeId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WarehouseType [warehouseTypeId=" + warehouseTypeId + ", name="
				+ name + ", description=" + description + "]";
	}
}
