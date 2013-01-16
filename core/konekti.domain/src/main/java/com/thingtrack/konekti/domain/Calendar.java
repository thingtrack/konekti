package com.thingtrack.konekti.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="CALENDAR")
public class Calendar implements Serializable {
	@Id
	@Column(name="CALENDAR_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer calendarId;
	
	@Column(name="NAME", unique=true, nullable=false, length=64)
	private String name;
	
	@Column(name="DESCRIPTION", length=256)
	private String description;
	
	@Lob
	@Column(name="LOGO")
	private byte[] logo;
	
	@Column(name="OBSERVATION", length=512)
	private String observation;
	
	@Column(name="CALENDAR_FROM_DATE", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date calendarFromDate;

	@Column(name="CALENDAR_TO_DATE", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date calendarToDate;
	
	@ManyToOne
	@JoinColumn(name="CALENDAR_TYPE_ID", nullable=false)
	private CalendarType calendarType;
	
	@OneToMany(mappedBy="calendar", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<CalendarCard> calendarCards = new ArrayList<CalendarCard>();
	
	@OneToMany(mappedBy="calendar", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<CalendarGroup> calendarGroups = new ArrayList<CalendarGroup>();
	
	@Column(name="ACTIVE", nullable=false)
	private boolean active = true;

	/**
	 * 
	 * @return the calendarId
	 */
	public Integer getCalendarId() {
		return calendarId;
	}

	/**
	 * @param calendarId the calendarId to set
	 */
	public void setCalendarId(Integer calendarId) {
		this.calendarId = calendarId;
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
	 * @return the logo
	 */
	public byte[] getLogo() {
		return logo;
	}

	/**
	 * @param logo the logo to set
	 */
	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	/**
	 * @return the calendarType
	 */
	public CalendarType getCalendarType() {
		return calendarType;
	}

	/**
	 * @param calendarType the calendarType to set
	 */
	public void setCalendarType(CalendarType calendarType) {
		this.calendarType = calendarType;
	}
	
	/**
	 * @return the calendarCards
	 */
	public List<CalendarCard> getCalendarCards() {
		return Collections.unmodifiableList(calendarCards);
		
	}

	/**
	 * @param CalendarCard the CalendarCard to set
	 */
	public void removeCalendarCard(CalendarCard calendarCard) {
		calendarCards.remove(calendarCard);
		
	}
	
	/**
	 * @param CalendarCard the CalendarCard to set
	 */
	public void addCalendarCard(CalendarCard calendarCard) {
		calendarCards.add(calendarCard);		
		
		if (calendarCard.getCalendar() != this)
			calendarCard.setCalendar(this);
		
	}
	
	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param observation the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}

	/**
	 * @return the observation
	 */
	public String getObservation() {
		return observation;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((calendarId == null) ? 0 : calendarId.hashCode());
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
		if (!(obj instanceof Calendar))
			return false;
		Calendar other = (Calendar) obj;
		if (calendarId == null) {
			if (other.calendarId != null)
				return false;
		} else if (!calendarId.equals(other.calendarId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Calendar [calendarId=" + calendarId + ", name=" + name
				+ ", description=" + description + ", observation=" + observation
				+ ", calendarType=" + calendarType + ", calendarCards="
				+ calendarCards + ", active=" + active + "]";
	}

	/**
	 * @return the calendarFromDate
	 */
	public Date getCalendarFromDate() {
		return calendarFromDate;
	}

	/**
	 * @param calendarFromDate the calendarFromDate to set
	 */
	public void setCalendarFromDate(Date calendarFromDate) {
		this.calendarFromDate = calendarFromDate;
	}

	/**
	 * @return the calendarToDate
	 */
	public Date getCalendarToDate() {
		return calendarToDate;
	}

	/**
	 * @param calendarToDate the calendarToDate to set
	 */
	public void setCalendarToDate(Date calendarToDate) {
		this.calendarToDate = calendarToDate;
	}

	/**
	 * @return the calendarGroups
	 */
	public List<CalendarGroup> getCalendarGroups() {
		return calendarGroups;
	}

	/**
	 * @param calendarGroups the calendarGroups to set
	 */
	public void setCalendarGroups(List<CalendarGroup> calendarGroups) {
		this.calendarGroups = calendarGroups;
	}

}
