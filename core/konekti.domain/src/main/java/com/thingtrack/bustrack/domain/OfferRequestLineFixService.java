package com.thingtrack.bustrack.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.thingtrack.konekti.domain.OfferRequestLine;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("FIX")
public class OfferRequestLineFixService extends OfferRequestLine {
	@Column(name="START_STOP")
	private String startStop;
	
	@Column(name="START_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	@Column(name="END_STOP")
	private String endStop;
	
	@Column(name="INTERMEDIATE_STOPS")
	private String intermediateStops;
	
	@Column(name="END_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	
	@Column(name="PASSENGERS")
	private Integer passengers;
		
	@Column(name="LUNCH", nullable=false)
	private boolean lunch=false;
	
	@Column(name="DINNER", nullable=false)
	private boolean dinner=false;
	
	@Column(name="BREACKFAST", nullable=false)
	private boolean breackfast=false;
	
	@Column(name="ACCOMODATION", nullable=false)
	private boolean accomodation=false;
			
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	 * @return the startStop
	 */
	public String getStartStop() {
		return startStop;
	}

	/**
	 * @param startStop the startStop to set
	 */
	public void setStartStop(String startStop) {
		this.startStop = startStop;
	}

	/**
	 * @return the endStop
	 */
	public String getEndStop() {
		return endStop;
	}

	/**
	 * @param endStop the endStop to set
	 */
	public void setEndStop(String endStop) {
		this.endStop = endStop;
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
}

