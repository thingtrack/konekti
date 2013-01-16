package com.thingtrack.konekti.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Calendar;

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
@Table(name="ALARM_JOB")
public class AlarmJob implements Serializable {
	@Id
	@Column(name="ALARM_JOB_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer alarmJobId;

	@Column(name="ALARM_NAME", nullable=false)
	private String alarmName;

	@Column(name="ALARM_GROUP", nullable=false)
	private String alarmGroup;
		
	@ManyToOne
	@JoinColumn(name = "LOCATION_ID")
	private Location location;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="START_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
	
	@Column(name="END_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;
	
	@Column(name="ALARM_TRIGGER_PRIORITY", nullable=false)
	private Integer alarmTriggerPriority = 5;
		
	@Column(name="ALARM_INTERVAL")
	private Integer alarmInterval = 1;
	
	@ManyToOne
	@JoinColumn(name = "ALARM_TRIGGER_TYPE_ID", nullable = false)
	private AlarmTriggerType alarmTriggerType;
	
	@Column(name="REPEAT_COUNT")
	private Integer repeatCount;
	
	@Column(name="ALARM_CALENDAR")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar alarmCalendar;
	
	@Column(name="FUTURE", nullable=false)
	private Boolean future = false;
	
	@Column(name="FUTURE_TIME")	
	private Integer future_time;
	
	@Column(name="CRON_EXPRESSION")
	private String cronExpression;

	@Column(name="LAST_EXECUTION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastExecution;
	
	@Column(name="ERROR", nullable=false)
	private Boolean error = false;
	
	@Column(name="ACTIVE", nullable=false)
	private Boolean active = false;

	public enum ALARM_TRIGGER_TYPE{		
		SIMPLE,
		CRON
	} 
	
	/**
	 * @return the alarmJobId
	 */
	public Integer getAlarmJobId() {
		return alarmJobId;
	}

	/**
	 * @param alarmJobId the alarmJobId to set
	 */
	public void setAlarmJobId(Integer alarmJobId) {
		this.alarmJobId = alarmJobId;
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
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the alarmInterval
	 */
	public Integer getAlarmInterval() {
		return alarmInterval;
	}

	/**
	 * @param alarmInterval the alarmInterval to set
	 */
	public void setAlarmInterval(Integer alarmInterval) {
		this.alarmInterval = alarmInterval;
	}

	/**
	 * @return the repeatCount
	 */
	public Integer getRepeatCount() {
		return repeatCount;
	}

	/**
	 * @param repeatCount the repeatCount to set
	 */
	public void setRepeatCount(Integer repeatCount) {
		this.repeatCount = repeatCount;
	}

	/**
	 * @return the alarmCalendar
	 */
	public Calendar getAlarmCalendar() {
		return alarmCalendar;
	}

	/**
	 * @param alarmCalendar the alarmCalendar to set
	 */
	public void setAlarmCalendar(Calendar alarmCalendar) {
		this.alarmCalendar = alarmCalendar;
	}

	/**
	 * @return the future
	 */
	public Boolean getFuture() {
		return future;
	}

	/**
	 * @param future the future to set
	 */
	public void setFuture(Boolean future) {
		this.future = future;
	}

	/**
	 * @return the future_time
	 */
	public Integer getFuture_time() {
		return future_time;
	}

	/**
	 * @param future_time the future_time to set
	 */
	public void setFuture_time(Integer future_time) {
		this.future_time = future_time;
	}

	/**
	 * @return the cronExpression
	 */
	public String getCronExpression() {
		return cronExpression;
	}

	/**
	 * @param cronExpression the cronExpression to set
	 */
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	/**
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((alarmGroup == null) ? 0 : alarmGroup.hashCode());
		result = prime * result
				+ ((alarmJobId == null) ? 0 : alarmJobId.hashCode());
		result = prime * result
				+ ((alarmName == null) ? 0 : alarmName.hashCode());
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
		AlarmJob other = (AlarmJob) obj;
		if (alarmGroup == null) {
			if (other.alarmGroup != null)
				return false;
		} else if (!alarmGroup.equals(other.alarmGroup))
			return false;
		if (alarmJobId == null) {
			if (other.alarmJobId != null)
				return false;
		} else if (!alarmJobId.equals(other.alarmJobId))
			return false;
		if (alarmName == null) {
			if (other.alarmName != null)
				return false;
		} else if (!alarmName.equals(other.alarmName))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AlarmJob [alarmJobId=" + alarmJobId + ", alarmName="
				+ alarmName + ", alarmGroup=" + alarmGroup + "]";
	}

	/**
	 * @return the alarmTriggerType
	 */
	public AlarmTriggerType getAlarmTriggerType() {
		return alarmTriggerType;
	}

	/**
	 * @param alarmTriggerType the alarmTriggerType to set
	 */
	public void setAlarmTriggerType(AlarmTriggerType alarmTriggerType) {
		this.alarmTriggerType = alarmTriggerType;
	}

	/**
	 * @return the alarmTriggerPriority
	 */
	public Integer getAlarmTriggerPriority() {
		return alarmTriggerPriority;
	}

	/**
	 * @param alarmTriggerPriority the alarmTriggerPriority to set
	 */
	public void setAlarmTriggerPriority(Integer alarmTriggerPriority) {
		this.alarmTriggerPriority = alarmTriggerPriority;
	}

	/**
	 * @return the lastExecution
	 */
	public Date getLastExecution() {
		return lastExecution;
	}

	/**
	 * @param lastExecution the lastExecution to set
	 */
	public void setLastExecution(Date lastExecution) {
		this.lastExecution = lastExecution;
	}

	/**
	 * @return the error
	 */
	public Boolean getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(Boolean error) {
		this.error = error;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}


}
