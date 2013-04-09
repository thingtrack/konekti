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
@Table(name="ALARM")
public class Alarm implements Serializable {
	@Id
	@Column(name="ALARM_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer alarmId;
	
	@Column(name="ALARM_JOB_NAME", nullable=false)
	private String alarmName;

	@Column(name="ALARM_JOB_GROUP", nullable=false)
	private String alarmGroup;
	
	@Column(name="MESSAGE", length=500)
	private String message;
	
	@ManyToOne
	@JoinColumn(name="ALARM_TYPE_ID", nullable=false)
	private AlarmType alarmType;
	
	@ManyToOne
	@JoinColumn(name="AREA_ID")
	private Area area;
	
	@ManyToOne
	@JoinColumn(name="ALARM_STATUS_ID", nullable=false)
	private AlarmStatus alarmStatus;
	
	@Column(name="ALARM_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date alarmDate;

	/**
	 * @param alarmId the alarmId to set
	 */
	public void setAlarmId(Integer alarmId) {
		this.alarmId = alarmId;
	}

	/**
	 * @return the alarmId
	 */
	public Integer getAlarmId() {
		return alarmId;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param alarmType the alarmType to set
	 */
	public void setAlarmType(AlarmType alarmType) {
		this.alarmType = alarmType;
	}

	/**
	 * @return the alarmType
	 */
	public AlarmType getAlarmType() {
		return alarmType;
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

	/**
	 * @param alarmStatus the alarmStatus to set
	 */
	public void setAlarmStatus(AlarmStatus alarmStatus) {
		this.alarmStatus = alarmStatus;
	}

	/**
	 * @return the alarmStatus
	 */
	public AlarmStatus getAlarmStatus() {
		return alarmStatus;
	}

	/**
	 * @param alarmDate the alarmDate to set
	 */
	public void setAlarmDate(Date alarmDate) {
		this.alarmDate = alarmDate;
	}

	/**
	 * @return the alarmDate
	 */
	public Date getAlarmDate() {
		return alarmDate;
	}

	/**
	 * @return the alarmName
	 */
	public String getAlarmName() {
		return alarmName;
	}

	/**
	 * @param alarmName the alarmName to set
	 */
	public void setAlarmName(String alarmName) {
		this.alarmName = alarmName;
	}

	/**
	 * @return the alarmGroup
	 */
	public String getAlarmGroup() {
		return alarmGroup;
	}

	/**
	 * @param alarmGroup the alarmGroup to set
	 */
	public void setAlarmGroup(String alarmGroup) {
		this.alarmGroup = alarmGroup;
	}
}
