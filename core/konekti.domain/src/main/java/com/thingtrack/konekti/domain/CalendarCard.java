package com.thingtrack.konekti.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="CALENDAR_CARD")
public class CalendarCard implements Serializable {
	@Id
	@Column(name="CALENDAR_CARD_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer calendarCardId;
	
	@Column(name="CODE", nullable=false)
	private String code;
	
	@Column(name="YEAR", nullable=false)
	private int year;

	@Column(name="MONTH", nullable=false)
	private int month;
	
	@Column(name="DESCRIPTION", length=256)
	private String description;

	@OneToMany(mappedBy="calendarCard", cascade=CascadeType.ALL)
	private List<CalendarDetail> calendarDetails = new ArrayList<CalendarDetail>();
	
	@ManyToOne
	@JoinColumn(name="CALENDAR_ID")
	private Calendar calendar;
	
	/**
	 * @param calendarCardId the calendarCardId to set
	 */
	public void setCalendarCardId(Integer calendarCardId) {
		this.calendarCardId = calendarCardId;
	}

	/**
	 * @return the calendarCardId
	 */
	public Integer getCalendarCardId() {
		return calendarCardId;
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
	 * @return the calendarDetails
	 */
	public List<CalendarDetail> getCalendarDetails() {
		return Collections.unmodifiableList(calendarDetails);
		
	}

	/**
	 * @param CalendarCard the CalendarCard to set
	 */
	public void removeCalendarDetail(CalendarDetail calendarDetail) {
		calendarDetails.remove(calendarDetail);
		
	}
	
	/**
	 * @param calendarDetails the calendarDetails to set
	 */
	public void addCalendarDetail(CalendarDetail calendarDetail) {
		calendarDetails.add(calendarDetail);		
		
		if (calendarDetail.getCalendarCard() != this)
			calendarDetail.setCalendarCard(this);
		
	}
	
	/**
	 * @param calendar the calendar to set
	 */
	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	/**
	 * @return the calendar
	 */
	public Calendar getCalendar() {
		return calendar;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((calendarCardId == null) ? 0 : calendarCardId.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		CalendarCard other = (CalendarCard) obj;
		if (calendarCardId == null) {
			if (other.calendarCardId != null)
				return false;
		} else if (!calendarCardId.equals(other.calendarCardId))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CalendarCard [calendarCardId=" + calendarCardId + ", code="
				+ code + ", description=" + description + ", calendarDetails="
				+ calendarDetails + ", calendar=" + calendar + "]";
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(int month) {
		this.month = month;
	}

}
