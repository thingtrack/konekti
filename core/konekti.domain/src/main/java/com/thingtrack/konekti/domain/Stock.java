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
@Table(name="STOCK")
public class Stock implements Serializable {
	@Id
	@Column(name="STOCK_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer stockId;
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID", nullable=false)	
	private Product product;
	
	@Column(name="QUANTITY", length = 10, precision = 2)
	private double quantity;
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_UNIT_ID", nullable=false)
	private ProductUnit productUnit;
	
	@Column(name="PRICE", length = 10, precision = 2)
	private double price;
	
	@Column(name="SERIAL_NUMBER", length = 10)
	private String serialNumber;
	
	@Column(name="LOTE", length = 10)
	private String lote;

	@Column(name="EXPEDITION_DATE")
	@Temporal(TemporalType.DATE)
	private Date expeditionDate;
	
	@ManyToOne
	@JoinColumn(name="AREA_ID", nullable=false)
	private Area area;
	
	@Column(name="COMMENT", length=512)
	private String comment;
	
	@ManyToOne
	@JoinColumn(name="STOCK_STATUS_ID", nullable=false)
	private StockStatus stockStatus;
	
	@Column(name="STOCK_DATE", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date stockDate;

	public enum STATUS {        
		WAREHOUSED,
		BROKEN,
		QUARANTINE,
		WET,
		DECAYED
    }
	
	/**
	 * @param stockId the stockId to set
	 */
	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	/**
	 * @return the stockId
	 */
	public Integer getStockId() {
		return stockId;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the quantity
	 */
	public double getQuantity() {
		return quantity;
	}

	/**
	 * @param productUnit the productUnit to set
	 */
	public void setProductUnit(ProductUnit productUnit) {
		this.productUnit = productUnit;
	}

	/**
	 * @return the productUnit
	 */
	public ProductUnit getProductUnit() {
		return productUnit;
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
	 * @param stockStatus the stockStatus to set
	 */
	public void setStockStatus(StockStatus stockStatus) {
		this.stockStatus = stockStatus;
	}

	/**
	 * @return the stockStatus
	 */
	public StockStatus getStockStatus() {
		return stockStatus;
	}

	/**
	 * @param stockDate the stockDate to set
	 */
	public void setStockDate(Date stockDate) {
		this.stockDate = stockDate;
	}

	/**
	 * @return the stockDate
	 */
	public Date getStockDate() {
		return stockDate;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(Area area) {
		this.area = area;
	}

	/**
	 * @return the area
	 */
	public Area getArea() {
		return area;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stockId == null) ? 0 : stockId.hashCode());
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
		if (!(obj instanceof Stock))
			return false;
		Stock other = (Stock) obj;
		if (stockId == null) {
			if (other.stockId != null)
				return false;
		} else if (!stockId.equals(other.stockId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Stock [stockId=" + stockId + ", product=" + product
				+ ", quantity=" + quantity + ", productUnit=" + productUnit
				+ ", price=" + price
				+ ", comment=" + comment + ", stockStatus=" + stockStatus
				+ ", stockDate=" + stockDate + "]";
	}

	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @param serialNumber the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * @return the lote
	 */
	public String getLote() {
		return lote;
	}

	/**
	 * @param lote the lote to set
	 */
	public void setLote(String lote) {
		this.lote = lote;
	}

	/**
	 * @return the expeditionDate
	 */
	public Date getExpeditionDate() {
		return expeditionDate;
	}

	/**
	 * @param expeditionDate the expeditionDate to set
	 */
	public void setExpeditionDate(Date expeditionDate) {
		this.expeditionDate = expeditionDate;
	}
}
