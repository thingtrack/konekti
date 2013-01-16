package com.thingtrack.bustrack.domain.dto;

import java.io.Serializable;
import java.util.Date;

import com.thingtrack.konekti.domain.Offer;
import com.thingtrack.konekti.domain.OfferLine;
import com.thingtrack.konekti.domain.Service;


@SuppressWarnings("serial")
public class ServiceDto implements Serializable {
	/**
	 * @return the offerDate
	 */
	public Date getOfferDate() {
		return offerDate;
	}

	/**
	 * @param offerDate the offerDate to set
	 */
	public void setOfferDate(Date offerDate) {
		this.offerDate = offerDate;
	}

	private Offer offer;
	private OfferLine offerLine;
	private Service service;	
	private String offerCode;
	private String offerRevision;
	private Date offerDate;
	private String offerTypeDescription;
	private String offerClientName;
	private String offerClientVat;
	private String offerStatusDescription;
	private Number offerLineNumber;
	private String offerLineStatusDescription;
	private String serviceCode;
	
	public ServiceDto (Offer offer,
					   OfferLine offerLine,
					   Service service,
			           String offerCode,
			     	   String offerRevision,
			     	   Date offerDate,
			     	   String offerTypeDescription,
			     	   String offerClientName,
			     	   String offerClientVat,
			     	   String offerStatusDescription,
			     	   Number offerLineNumber,
			     	   String offerLineStatusDescription,
			     	   String serviceCode) {
		
		this.offerCode = offerCode;
		this.offerRevision = offerRevision;
		this.offerDate = offerDate;
		this.offerTypeDescription = offerTypeDescription;
		this.offerClientName = offerClientName;
		this.offerClientVat = offerClientVat;
		this.offerStatusDescription = offerStatusDescription;
		this.offerLineNumber = offerLineNumber;
		this.offerLineStatusDescription = offerLineStatusDescription;
		this.serviceCode = serviceCode;
	}
	
	/**
	 * @return the offer
	 */
	public Offer getOffer() {
		return offer;
	}

	/**
	 * @param offer the offer to set
	 */
	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	/**
	 * @return the offerLine
	 */
	public OfferLine getOfferLine() {
		return offerLine;
	}

	/**
	 * @param offerLine the offerLine to set
	 */
	public void setOfferLine(OfferLine offerLine) {
		this.offerLine = offerLine;
	}

	/**
	 * @return the service
	 */
	public Service getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */
	public void setService(Service service) {
		this.service = service;
	}

	/**
	 * @return the offerCode
	 */
	public String getOfferCode() {
		return offerCode;
	}

	/**
	 * @param offerCode the offerCode to set
	 */
	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	/**
	 * @return the offerRevision
	 */
	public String getOfferRevision() {
		return offerRevision;
	}

	/**
	 * @param offerRevision the offerRevision to set
	 */
	public void setOfferRevision(String offerRevision) {
		this.offerRevision = offerRevision;
	}

	/**
	 * @return the offerTypeDescription
	 */
	public String getOfferTypeDescription() {
		return offerTypeDescription;
	}

	/**
	 * @param offerTypeDescription the offerTypeDescription to set
	 */
	public void setOfferTypeDescription(String offerTypeDescription) {
		this.offerTypeDescription = offerTypeDescription;
	}

	/**
	 * @return the offerClientName
	 */
	public String getOfferClientName() {
		return offerClientName;
	}

	/**
	 * @param offerClientName the offerClientName to set
	 */
	public void setOfferClientName(String offerClientName) {
		this.offerClientName = offerClientName;
	}

	/**
	 * @return the offerClientVat
	 */
	public String getOfferClientVat() {
		return offerClientVat;
	}

	/**
	 * @param offerClientVat the offerClientVat to set
	 */
	public void setOfferClientVat(String offerClientVat) {
		this.offerClientVat = offerClientVat;
	}

	/**
	 * @return the offerStatusDescription
	 */
	public String getOfferStatusDescription() {
		return offerStatusDescription;
	}

	/**
	 * @param offerStatusDescription the offerStatusDescription to set
	 */
	public void setOfferStatusDescription(String offerStatusDescription) {
		this.offerStatusDescription = offerStatusDescription;
	}

	/**
	 * @return the offerLineNumber
	 */
	public Number getOfferLineNumber() {
		return offerLineNumber;
	}

	/**
	 * @param offerLineNumber the offerLineNumber to set
	 */
	public void setOfferLineNumber(Number offerLineNumber) {
		this.offerLineNumber = offerLineNumber;
	}

	/**
	 * @return the offerLineStatusDescription
	 */
	public String getOfferLineStatusDescription() {
		return offerLineStatusDescription;
	}

	/**
	 * @param offerLineStatusDescription the offerLineStatusDescription to set
	 */
	public void setOfferLineStatusDescription(String offerLineStatusDescription) {
		this.offerLineStatusDescription = offerLineStatusDescription;
	}

	/**
	 * @return the serviceCode
	 */
	public String getServiceCode() {
		return serviceCode;
	}

	/**
	 * @param serviceCode the serviceCode to set
	 */
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
}
