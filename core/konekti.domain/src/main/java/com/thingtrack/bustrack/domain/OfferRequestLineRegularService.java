package com.thingtrack.bustrack.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.thingtrack.konekti.domain.Calendar;
import com.thingtrack.konekti.domain.OfferRequestLine;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("REGULAR")
public class OfferRequestLineRegularService extends OfferRequestLine {
	@ManyToOne
	@JoinColumn(name="CALENDAR_ID")
	private Calendar calendar;

	/**
	 * @return the calendar
	 */
	public Calendar getCalendar() {
		return calendar;
	}

	/**
	 * @param calendar the calendar to set
	 */
	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

}
