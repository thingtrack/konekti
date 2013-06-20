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
import javax.persistence.FetchType;
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
@Table(name="SERVICE")
public class Service implements Serializable {
	@Id
	@Column(name="SERVICE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer serviceId;
	
	@ManyToOne
	@JoinColumn(name="ORGANIZATION_ID", nullable=false)
	private Organization organization;
	
	@Column(name="CODE", nullable=false, unique=true, length=64)
	private String code;
	
	@Column(name="DESCRIPTION", length=512)
	private String description;
	
	@ManyToOne
	@JoinColumn(name="SERVICE_TYPE_ID", nullable=false)
	private ServiceType serviceType;
		
	@ManyToOne
	@JoinColumn(name="CLIENT_ID")
	private Client client;

	@ManyToOne
	@JoinColumn(name="OFFER_LINE_ID")
	private OfferLine offerLine;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="INVOICE_LINE_ID")
	private InvoiceLine invoiceLine;
	
	@Column(name="INTERMEDIATE_STOPS")
	private String intermediateStops;
	
	@Column(name="STOP_NUMBER")
	private Integer stopNumber;
	
	@Column(name="PASSENGERS")
	private Integer passengers;
	
	@Column(name="DRIVER_RPM_QUALITY", length = 10, precision = 2)
	private double driverRPMQuality;
	
	@Column(name="KM_OFFER", length = 10, precision = 2)
	private double kmOffer;
	
	@Column(name="KM_REAL", length = 10, precision = 2)
	private double kmReal;
	
	@Column(name="GAS_OFFER", length = 10, precision = 2)
	private double gasOffer;
	
	@Column(name="GAS_REAL", length = 10, precision = 2)
	private double gasReal;
	
	@Column(name="START_SERVICE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startService;
	
	@Column(name="STOP_SERVICE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date stopService;
	
	@Column(name="LUNCH", nullable=false)
	private boolean lunch=false;
	
	@Column(name="DINNER", nullable=false)
	private boolean dinner=false;
	
	@Column(name="BREACKFAST", nullable=false)
	private boolean breackfast=false;
	
	@Column(name="ACCOMODATION", nullable=false)
	private boolean accomodation=false;
	
	@Column(name="OBSERVATION", length=512)
	private String observation;
	
	@Column(name="RESERVATION_DATE", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date reservationDate=new Date();
	
	@Column(name="SCHEDULE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date scheduleDate;
		
	@ManyToOne(optional=false)
	@JoinColumn(name="SERVICE_STATUS_ID", nullable=false)
	private ServiceStatus serviceStatus;
	
	@Column(name="ROUTE_TYPE")
	private String routeType;

	@Column(name="ROUTE_AVOIDS")
	private String routeAvoids;
	
	@Column(name="TEMPLATE")
	private boolean template;
	
	public enum STATUS {        
        OPENED,
        DESIGNED,
        PLANNED,
        RUNNING,
        PAUSED,
        REJECTED,
        CLOSED
    }
		
	public enum TYPE {
		OTHER
	}
	
	public enum ROUTE_TYPE{
		FASTEST,
		SHORTEST
	}
	
	public enum ROUTE_AVOIDS{
		AVOID_HIGHWAYS('H'),
		AVOID_TOLLS('T'),
		AVOID_UNPAVED('U'),
		AVOID_FERRIES('F'),
		AVOID_COUNTRY_BORDERS('C'),
		AVOID_SEASONAL_ROADS('R'),
		AVOID_TIMED_RESTRICTIONS('I');
		
		private char value;
		
		public char getValue(){
			return value;	
		}
		
		ROUTE_AVOIDS(char value){
			this.value = value;
		}
	}
	
	public Service() {
		
	}
	
	public Service(String code, Date reservationDate, Organization organization, ServiceType serviceType, ServiceStatus serviceStatus) {
		this.code = code;
		this.reservationDate = reservationDate;
		this.organization = organization;
		this.serviceType = serviceType;
		this.serviceStatus = serviceStatus;
		
	}
	
	/**
	 * @param serviceId the serviceId to set
	 */
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	/**
	 * @return the serviceId
	 */
	public Integer getServiceId() {
		return serviceId;
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
	 * @return the serviceType
	 */
	public ServiceType getServiceType() {
		return serviceType;
	}

	/**
	 * @param serviceType the serviceType to set
	 */
	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	/**
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * @return the reservationDate
	 */
	public Date getReservationDate() {
		return reservationDate;
	}

	/**
	 * @param reservationDate the reservationDate to set
	 */
	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	/**
	 * @return the scheduleDate
	 */
	public Date getScheduleDate() {
		return scheduleDate;
	}

	/**
	 * @param scheduleDate the scheduleDate to set
	 */
	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	/**
	 * @return the serviceStatus
	 */
	public ServiceStatus getServiceStatus() {
		return serviceStatus;
	}

	/**
	 * @param serviceStatus the serviceStatus to set
	 */
	public void setServiceStatus(ServiceStatus serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	public String getRouteType() {
		return routeType;
	}

	public void setRouteType(String routeType) {
		this.routeType = routeType;
	}

	public String getRouteAvoids() {
		return routeAvoids;
	}

	public void setRouteAvoids(String routeAvoids) {
		this.routeAvoids = routeAvoids;
	}

	/**
	 * @param offerLine the offerLine to set
	 */
	public void setOfferLine(OfferLine offerLine) {
		this.offerLine = offerLine;
	}

	/**
	 * @return the offerLine
	 */
	public OfferLine getOfferLine() {
		return offerLine;
	}

	/**
	 * @param organization the organization to set
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	/**
	 * @return the organization
	 */
	public Organization getOrganization() {
		return organization;
	}

	/**
	 * @return the observation
	 */
	public String getObservation() {
		return observation;
	}

	/**
	 * @param observation the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}

	/**
	 * @return the stopNumber
	 */
	public Integer getStopNumber() {
		return stopNumber;
	}

	/**
	 * @param stopNumber the stopNumber to set
	 */
	public void setStopNumber(Integer stopNumber) {
		this.stopNumber = stopNumber;
	}

	/**
	 * @return the passengers
	 */
	public Integer getPassengers() {
		return passengers;
	}

	/**
	 * @param passengers the passengers to set
	 */
	public void setPassengers(Integer passengers) {
		this.passengers = passengers;
	}

	/**
	 * @return the driverRPMQuality
	 */
	public double getDriverRPMQuality() {
		return driverRPMQuality;
	}

	/**
	 * @param driverRPMQuality the driverRPMQuality to set
	 */
	public void setDriverRPMQuality(double driverRPMQuality) {
		this.driverRPMQuality = driverRPMQuality;
	}

	/**
	 * @return the kmOffer
	 */
	public double getKmOffer() {
		return kmOffer;
	}

	/**
	 * @param kmOffer the kmOffer to set
	 */
	public void setKmOffer(double kmOffer) {
		this.kmOffer = kmOffer;
	}

	/**
	 * @return the kmReal
	 */
	public double getKmReal() {
		return kmReal;
	}

	/**
	 * @param kmReal the kmReal to set
	 */
	public void setKmReal(double kmReal) {
		this.kmReal = kmReal;
	}

	/**
	 * @return the gasOffer
	 */
	public double getGasOffer() {
		return gasOffer;
	}

	/**
	 * @param gasOffer the gasOffer to set
	 */
	public void setGasOffer(double gasOffer) {
		this.gasOffer = gasOffer;
	}

	/**
	 * @return the gasReal
	 */
	public double getGasReal() {
		return gasReal;
	}

	/**
	 * @param gasReal the gasReal to set
	 */
	public void setGasReal(double gasReal) {
		this.gasReal = gasReal;
	}

	/**
	 * @return the startService
	 */
	public Date getStartService() {
		return startService;
	}

	/**
	 * @param startService the startService to set
	 */
	public void setStartService(Date startService) {
		this.startService = startService;
	}

	/**
	 * @param invoiceLine the invoiceLine to set
	 */
	public void setInvoiceLine(InvoiceLine invoiceLine) {
		this.invoiceLine = invoiceLine;
	}

	/**
	 * @return the invoiceLine
	 */
	public InvoiceLine getInvoiceLine() {
		return invoiceLine;
	}

	/**
	 * @param stopService the stopService to set
	 */
	public void setStopService(Date stopService) {
		this.stopService = stopService;
	}

	/**
	 * @return the stopService
	 */
	public Date getStopService() {
		return stopService;
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
				+ ((serviceId == null) ? 0 : serviceId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		//Type check
		if (!(obj instanceof Service))
			return false;
		Service other = (Service) obj;
				
		//Code check
		if (code == null && other.code != null)
			return false;
		
		if (!code.equals(other.code))
			return false;
		
		//Service Id check
		if (serviceId == null && other.serviceId != null)
			return false;
			
		if (!serviceId.equals(other.serviceId))
			return false;
		
		return super.equals(obj);
	}	
	
	@Override
	public String toString() {
		return "Service [serviceId=" + serviceId + ", code=" + code + "]";
		
	}
	
	/**
	 * @return the intermediateStops
	 */
	public String getIntermediateStops() {
		return intermediateStops;
	}

	/**
	 * @param intermediateStops the intermediateStops to set
	 */
	public void setIntermediateStops(String intermediateStops) {
		this.intermediateStops = intermediateStops;
	}

	/**
	 * @return the lunch
	 */
	public boolean isLunch() {
		return lunch;
	}

	/**
	 * @param lunch the lunch to set
	 */
	public void setLunch(boolean lunch) {
		this.lunch = lunch;
	}

	/**
	 * @return the dinner
	 */
	public boolean isDinner() {
		return dinner;
	}

	/**
	 * @param dinner the dinner to set
	 */
	public void setDinner(boolean dinner) {
		this.dinner = dinner;
	}

	/**
	 * @return the accomodation
	 */
	public boolean isAccomodation() {
		return accomodation;
	}

	/**
	 * @param accomodation the accomodation to set
	 */
	public void setAccomodation(boolean accomodation) {
		this.accomodation = accomodation;
	}

	/**
	 * @return the breackfast
	 */
	public boolean isBreackfast() {
		return breackfast;
	}

	/**
	 * @param breackfast the breackfast to set
	 */
	public void setBreackfast(boolean breackfast) {
		this.breackfast = breackfast;
	}
	
	/**
	 * @return the template
	 */
	public boolean isTemplate() {
		return template;
	}

	/**
	 * @param template the template to set
	 */
	public void setTemplate(boolean template) {
		this.template = template;
	}
}
