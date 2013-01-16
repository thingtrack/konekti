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
@Table(name="CALENDAR_DETAIL")
public class CalendarDetail implements Serializable {
	@Id
	@Column(name="CALENDAR_DETAIL_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer calendarDetailId;
	
	@ManyToOne
	@JoinColumn(name="CALENDAR_CARD_ID", nullable=false)
	private CalendarCard calendarCard;
	
	@Column(name="DATE", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@ManyToOne
	@JoinColumn(name="CALENDAR_GROUP_ID", nullable=false)
	private CalendarGroup calendarGroup;
		
	/**
	 * @return the calendarDetailId
	 */
	public Integer getCalendarDetailId() {
		return calendarDetailId;
	}

	/**
	 * @param calendarDetailId the calendarDetailId to set
	 */
	public void setCalendarDetailId(Integer calendarDetailId) {
		this.calendarDetailId = calendarDetailId;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @param calendarCard the calendarCard to set
	 */
	public void setCalendarCard(CalendarCard calendarCard) {
		this.calendarCard = calendarCard;
	}

	/**
	 * @return the calendarCard
	 */
	public CalendarCard getCalendarCard() {
		return calendarCard;
	}
		
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((calendarDetailId == null) ? 0 : calendarDetailId.hashCode());
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
		if (!(obj instanceof CalendarDetail))
			return false;
		CalendarDetail other = (CalendarDetail) obj;
		if (calendarDetailId == null) {
			if (other.calendarDetailId != null)
				return false;
		} else if (!calendarDetailId.equals(other.calendarDetailId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CalendarDetail [calendarDetailId=" + calendarDetailId
				+ ", calendarCard=" + calendarCard + ", date=" + date
				+ "]";
	}

	/**
	 * @return the calendarGroup
	 */
	public CalendarGroup getCalendarGroup() {
		return calendarGroup;
	}

	/**
	 * @param calendarGroup the calendarGroup to set
	 */
	public void setCalendarGroup(CalendarGroup calendarGroup) {
		this.calendarGroup = calendarGroup;
	}
}
