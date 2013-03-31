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
@Table(name="JOB")
public class Job implements Serializable {
	@Id
	@Column(name="JOB_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer jobId;

	@Column(name="JOB_NAME", nullable=false)
	private String jobName;

	@Column(name="JOB_GROUP", nullable=false)
	private String jobGroup;
		
	@ManyToOne
	@JoinColumn(name = "AREA_ID")
	private Area area;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="START_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
	
	@Column(name="END_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;
	
	@Column(name="JOB_TRIGGER_PRIORITY", nullable=false)
	private Integer jobTriggerPriority = 5;
		
	@Column(name="JOB_INTERVAL")
	private Integer jobInterval = 1;
	
	@ManyToOne
	@JoinColumn(name = "JOB_TRIGGER_TYPE_ID", nullable = false)
	private JobTriggerType jobTriggerType;
	
	@Column(name="REPEAT_COUNT")
	private Integer repeatCount;
	
	@Column(name="JOB_CALENDAR")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar jobCalendar;
	
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

	public enum JOB_TRIGGER_TYPE{		
		SIMPLE,
		CRON
	} 
	
	/**
	 * @return the JobId
	 */
	public Integer getJobId() {
		return jobId;
	}

	/**
	 * @param jobId the jobId to set
	 */
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	/**
	 * @return the jobName
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * @param jobName the jobName to set
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	/**
	 * @return the jobGroup
	 */
	public String getJobGroup() {
		return jobGroup;
	}

	/**
	 * @param jobGroup the jobGroup to set
	 */
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
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
	 * @return the jobInterval
	 */
	public Integer getJobInterval() {
		return jobInterval;
	}

	/**
	 * @param jobInterval the jobInterval to set
	 */
	public void setJobInterval(Integer jobInterval) {
		this.jobInterval = jobInterval;
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
	 * @return the jobCalendar
	 */
	public Calendar getJobCalendar() {
		return jobCalendar;
	}

	/**
	 * @param jobCalendar the jobCalendar to set
	 */
	public void setJobCalendar(Calendar jobCalendar) {
		this.jobCalendar = jobCalendar;
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
				+ ((jobGroup == null) ? 0 : jobGroup.hashCode());
		result = prime * result
				+ ((jobId == null) ? 0 : jobId.hashCode());
		result = prime * result
				+ ((jobName == null) ? 0 : jobName.hashCode());
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
		Job other = (Job) obj;
		if (jobGroup == null) {
			if (other.jobGroup != null)
				return false;
		} else if (!jobGroup.equals(other.jobGroup))
			return false;
		if (jobId == null) {
			if (other.jobId != null)
				return false;
		} else if (!jobId.equals(other.jobId))
			return false;
		if (jobName == null) {
			if (other.jobName != null)
				return false;
		} else if (!jobName.equals(other.jobName))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Job [jobId=" + jobId + ", jobName="
				+ jobName + ", jobGroup=" + jobGroup + "]";
	}

	/**
	 * @return the jobTriggerType
	 */
	public JobTriggerType getJobTriggerType() {
		return jobTriggerType;
	}

	/**
	 * @param jobTriggerType the jobTriggerType to set
	 */
	public void setJobTriggerType(JobTriggerType jobTriggerType) {
		this.jobTriggerType = jobTriggerType;
	}

	/**
	 * @return the jobTriggerPriority
	 */
	public Integer getJobTriggerPriority() {
		return jobTriggerPriority;
	}

	/**
	 * @param jobTriggerPriority the jobTriggerPriority to set
	 */
	public void setJobTriggerPriority(Integer jobTriggerPriority) {
		this.jobTriggerPriority = jobTriggerPriority;
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
	 * @return the area
	 */
	public Area getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(Area area) {
		this.area = area;
	}


}
