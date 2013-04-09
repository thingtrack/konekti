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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="PRODUCT")
public class Product implements Serializable {
	@Id
	@Column(name="PRODUCT_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer productId;
	
	@Column(name="CODE", nullable=false)
	private String code;
	
	@Column(name="NAME", nullable=false)
	private String name;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_TYPE_ID", nullable=false)
	private ProductType productType;
	
	@Column(name="HAS_LOTE", nullable=false)
	private Boolean hasLote = false;
	
	@Column(name="HAS_SERIAL_NUMBER", nullable=false)
	private Boolean hasSerialNumber = false;
	
	@Column(name="HAS_EXPEDITION_DATE", nullable=false)
	private Boolean hasExpeditionDate = false;
	
	@Column(name="AVERAGE_PRICE",nullable=false)
	private float averagePrice;
	
	@ManyToMany
	@JoinTable(name="PRODUCT_AREA",
			   joinColumns=@JoinColumn(name="PRODUCT_ID"),
			   inverseJoinColumns=@JoinColumn(name="AREA_ID"))	
	private List<Area> areas = new ArrayList<Area>();
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_SUPPLIER_ID")	
	private Supplier productSupplier;

	@ManyToMany
	@JoinTable(name="PRODUCT_PRODUCT_UNIT",
			   joinColumns=@JoinColumn(name="PRODUCT_ID"),
			   inverseJoinColumns=@JoinColumn(name="PRODUCT_UNIT_ID"))	
	private List<ProductUnit> productUnits = new ArrayList<ProductUnit>();
	
	@Column(name="VERSION")
	private String version;
	
	@Column(name="PRODUCT_ACTIVE", nullable=false)
	private Boolean productActive = true;

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	/**
	 * @return the productId
	 */
	public Integer getProductId() {
		return productId;
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

	/**
	 * @param productType the productType to set
	 */
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	/**
	 * @return the productType
	 */
	public ProductType getProductType() {
		return productType;
	}

	/**
	 * @param hasLote the hasLote to set
	 */
	public void setHasLote(Boolean hasLote) {
		this.hasLote = hasLote;
	}

	/**
	 * @return the hasLote
	 */
	public Boolean getHasLote() {
		return hasLote;
	}

	/**
	 * @param hasExpeditionDate the hasExpeditionDate to set
	 */
	public void setHasExpeditionDate(Boolean hasExpeditionDate) {
		this.hasExpeditionDate = hasExpeditionDate;
	}

	/**
	 * @return the hasExpeditionDate
	 */
	public Boolean getHasExpeditionDate() {
		return hasExpeditionDate;
	}

	/**
	 * @param averagePrice the averagePrice to set
	 */
	public void setAveragePrice(float averagePrice) {
		this.averagePrice = averagePrice;
	}

	/**
	 * @return the averagePrice
	 */
	public float getAveragePrice() {
		return averagePrice;
	}

	/**
	 * @param areas the areas to set
	 */
	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	/**
	 * @return the areas
	 */
	public List<Area> getAreas() {
		return areas;
	}

	/**
	 * @param productSupplier the productSupplier to set
	 */
	public void setProductSupplier(Supplier productSupplier) {
		this.productSupplier = productSupplier;
	}

	/**
	 * @return the productSupplier
	 */
	public Supplier getProductSupplier() {
		return productSupplier;
	}

	/**
	 * @param productUnits the productUnits to set
	 */
	public void setProductUnits(List<ProductUnit> productUnits) {
		this.productUnits = productUnits;
	}

	/**
	 * @return the productUnits
	 */
	public List<ProductUnit> getProductUnits() {
		return productUnits;
	}

	/**
	 * @param hasSerialNumber the hasSerialNumber to set
	 */
	public void setHasSerialNumber(Boolean hasSerialNumber) {
		this.hasSerialNumber = hasSerialNumber;
	}

	/**
	 * @return the hasSerialNumber
	 */
	public Boolean getHasSerialNumber() {
		return hasSerialNumber;
	}

	/**
	 * @param productActive the productActive to set
	 */
	public void setProductActive(Boolean productActive) {
		this.productActive = productActive;
	}

	/**
	 * @return the productActive
	 */
	public Boolean getProductActive() {
		return productActive;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((productId == null) ? 0 : productId.hashCode());
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
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
}
