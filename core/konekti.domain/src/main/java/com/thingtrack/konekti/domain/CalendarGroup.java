package com.thingtrack.konekti.domain;

/*
 * #%L
 * Konekti Domain Layer
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2010 - 2014 Thingtrack s.l.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

/**
 * Entity class,
 * <p>
 * Represents the group of days has common features
 * @author carlos
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="CALENDAR_GROUP")
public class CalendarGroup implements Serializable {
	/**
	 * Unique identifier
	 */
	@Id
	@Column(name="CALENDAR_GROUP_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer calendarGroupId;
	
	/**
	 * {@link Calendar} owner
	 */
	@ManyToOne
	@JoinColumn(name="CALENDAR_ID")
	private Calendar calendar;
	
	/**
	 * Description, not null
	 */
	@Column(name="DESCRIPTION", nullable=false, length=512)
	private String description;
	
	/**
	 * Color
	 */
	@Column(name="COLOR", nullable=false, length=64)
	private String color;
	
	/**
	 * Working day, not null
	 */
	@Column(name="WORKING_DAY", nullable=false)
	private boolean workingDay = true;
	
	/**
	 * File content bytes
	 */
	@Column(name="FILE")
	@Lob
	private byte[] file;

	/**
	 * {@link CalendarDetail calendarDetails} contained 
	 */
	@OneToMany(mappedBy="calendarGroup", orphanRemoval=true)
	private List<CalendarDetail> calendarDetails = new ArrayList<CalendarDetail>();
	
	/**
	 * @return the calendarGroupId
	 */
	public Integer getCalendarGroupId() {
		return calendarGroupId;
	}

	/**
	 * @param calendarGroupId the calendarGroupId to set
	 */
	public void setCalendarGroupId(Integer calendarGroupId) {
		this.calendarGroupId = calendarGroupId;
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
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the file
	 */
	public byte[] getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(byte[] file) {
		this.file = file;
	}

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

	/**
	 * @return the workingDay
	 */
	public boolean isWorkingDay() {
		return workingDay;
	}

	/**
	 * @param workingDay the workingDay to set
	 */
	public void setWorkingDay(boolean workingDay) {
		this.workingDay = workingDay;
	}

	/**
	 * @return the calendarDetails
	 */
	public List<CalendarDetail> getCalendarDetails() {
		return calendarDetails;
	}

	/**
	 * @param calendarDetails the calendarDetails to set
	 */
	public void setCalendarDetails(List<CalendarDetail> calendarDetails) {
		this.calendarDetails = calendarDetails;
	}
}
