package com.thingtrack.konekti.message.internal.domain.csv.product;

import java.io.Serializable;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@SuppressWarnings("serial")
@CsvRecord(separator=";")
public class CsvProduct implements Serializable  {	
	@DataField(pos = 1, required = true)
	private String action;
		
	@DataField(pos = 2, required = true)
	private String code;
	
	@DataField(pos = 3, required = true)
	private String name;
	
	@DataField(pos = 4)
	private String version;
	
	@DataField(pos = 5, required = true)
	private String description;

	@DataField(pos = 6, required = true)
	private String typeCode;
	
	@DataField(pos = 7, required = true)
	private String hasLote;
	
	@DataField(pos = 8, required = true)
	private String hasSerialNumber;
	
	@DataField(pos = 9, required = true)
	private String hasExpeditionDate;
	
	@DataField(pos = 10, precision = 2)
	private String averagePrice;
	
	@DataField(pos = 11, required = true)
	private String supplierCode;
	
	@DataField(pos = 12, required = true)
	private String productUnitCode;
	
	@DataField(pos = 13, required = true)
	private String areaCode;
	
	@DataField(pos = 14, required = true)
	private String productActive;
	
	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
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

	/**
	 * @return the typeCode
	 */
	public String getTypeCode() {
		return typeCode;
	}

	/**
	 * @param typeCode the typeCode to set
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	/**
	 * @return the hasLote
	 */
	public String getHasLote() {
		return hasLote;
	}

	/**
	 * @param hasLote the hasLote to set
	 */
	public void setHasLote(String hasLote) {
		this.hasLote = hasLote;
	}

	/**
	 * @return the hasSerialNumber
	 */
	public String getHasSerialNumber() {
		return hasSerialNumber;
	}

	/**
	 * @param hasSerialNumber the hasSerialNumber to set
	 */
	public void setHasSerialNumber(String hasSerialNumber) {
		this.hasSerialNumber = hasSerialNumber;
	}

	/**
	 * @return the hasExpeditionDate
	 */
	public String getHasExpeditionDate() {
		return hasExpeditionDate;
	}

	/**
	 * @param hasExpeditionDate the hasExpeditionDate to set
	 */
	public void setHasExpeditionDate(String hasExpeditionDate) {
		this.hasExpeditionDate = hasExpeditionDate;
	}

	/**
	 * @return the averagePrice
	 */
	public String getAveragePrice() {
		return averagePrice;
	}

	/**
	 * @param averagePrice the averagePrice to set
	 */
	public void setAveragePrice(String averagePrice) {
		this.averagePrice = averagePrice;
	}

	/**
	 * @return the supplierCode
	 */
	public String getSupplierCode() {
		return supplierCode;
	}

	/**
	 * @param supplierCode the supplierCode to set
	 */
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	/**
	 * @return the productActive
	 */
	public String getProductActive() {
		return productActive;
	}

	/**
	 * @param productActive the productActive to set
	 */
	public void setProductActive(String productActive) {
		this.productActive = productActive;
	}

	/**
	 * @return the areaCode
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * @param areaCode the areaCode to set
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * @return the productUnitCode
	 */
	public String getProductUnitCode() {
		return productUnitCode;
	}

	/**
	 * @param productUnitCode the productUnitCode to set
	 */
	public void setProductUnitCode(String productUnitCode) {
		this.productUnitCode = productUnitCode;
	}
}
