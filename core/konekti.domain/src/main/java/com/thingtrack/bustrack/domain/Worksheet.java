package com.thingtrack.bustrack.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.thingtrack.konekti.domain.EmployeeAgent;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="WORKSHEET")
public class Worksheet implements Serializable {
	@Id
	@Column(name="WORKSHEET_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer worksheetId;
	
	@ManyToOne
	@JoinColumn(name="DRIVER_ID", nullable=false)
	private EmployeeAgent driver;
	
	@Column(name="WORKSHEET_START_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date worksheetStartDate;
	
	@Column(name="WORKSHEET_END_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date worksheetEndDate;
			
	@OneToMany(mappedBy="worksheet",fetch = FetchType.LAZY)
	private List<WorksheetLine> worksheetLines = new ArrayList<WorksheetLine>();

	@Column(name="OBSERVATION", length=512)
	private String observation;
	
	@Column(name="INCIDENCE", length=512)
	private String incidence;
	
	@Column(name="WORKSHEET_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date worksheetDate=new Date();

	@ManyToOne
	@JoinColumn(name="WORKSHEET_STATUS_ID", nullable=false)
	private WorksheetStatus worksheetstatus;

	public enum STATUS {
		OPEN,
		CLOSE
	}
	
	/**
	 * @return the worksheetId
	 */
	public Integer getWorksheetId() {
		return worksheetId;
	}

	/**
	 * @param worksheetId the worksheetId to set
	 */
	public void setWorksheetId(Integer worksheetId) {
		this.worksheetId = worksheetId;
	}

	/**
	 * @return the driver
	 */
	public EmployeeAgent getDriver() {
		return driver;
	}

	/**
	 * @param driver the driver to set
	 */
	public void setDriver(EmployeeAgent driver) {
		this.driver = driver;
	}

	/**
	 * @return the worksheetStartDate
	 */
	public Date getWorksheetStartDate() {
		return worksheetStartDate;
	}

	/**
	 * @param worksheetStartDate the worksheetStartDate to set
	 */
	public void setWorksheetStartDate(Date worksheetStartDate) {
		this.worksheetStartDate = worksheetStartDate;
	}

	/**
	 * @return the worksheetEndDate
	 */
	public Date getWorksheetEndDate() {
		return worksheetEndDate;
	}

	/**
	 * @param worksheetEndDate the worksheetEndDate to set
	 */
	public void setWorksheetEndDate(Date worksheetEndDate) {
		this.worksheetEndDate = worksheetEndDate;
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
	 * @return the worksheetLines
	 */
	public List<WorksheetLine> getWorksheetLines() {
		return Collections.unmodifiableList(worksheetLines);
	}
	
	
	public void setWorksheetLines(List<WorksheetLine> worksheetLines) {
	
		//Clean the list
		for(WorksheetLine worksheetLine : this.worksheetLines)
			removeWorksheetLine(worksheetLine);
		
		//Add the new worksheetlines
		for(WorksheetLine worksheetLine : worksheetLines)
			addWorksheetLine(worksheetLine);
	}
	
	public void addWorksheetLine(WorksheetLine worksheetLine){
		
		if(worksheetLines.contains(worksheetLine))
			return;
		
		worksheetLines.add(worksheetLine);
		worksheetLine.setWorksheet(this);
	}
	
	public void removeWorksheetLine(WorksheetLine worksheetLine){
		
		worksheetLines.remove(worksheetLine);
	}

	/**
	 * @return the worksheetDate
	 */
	public Date getWorksheetDate() {
		return worksheetDate;
	}

	/**
	 * @param worksheetDate the worksheetDate to set
	 */
	public void setWorksheetDate(Date worksheetDate) {
		this.worksheetDate = worksheetDate;
	}

	/**
	 * @return the worksheetstatus
	 */
	public WorksheetStatus getWorksheetstatus() {
		return worksheetstatus;
	}

	/**
	 * @param worksheetstatus the worksheetstatus to set
	 */
	public void setWorksheetstatus(WorksheetStatus worksheetstatus) {
		this.worksheetstatus = worksheetstatus;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((worksheetId == null) ? 0 : worksheetId.hashCode());
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
		if (!(obj instanceof Worksheet))
			return false;
		Worksheet other = (Worksheet) obj;
		if (worksheetId == null) {
			if (other.worksheetId != null)
				return false;
		} else if (!worksheetId.equals(other.worksheetId))
			return false;
		return true;
	}

	/**
	 * @param incidence the incidence to set
	 */
	public void setIncidence(String incidence) {
		this.incidence = incidence;
	}

	/**
	 * @return the incidence
	 */
	public String getIncidence() {
		return incidence;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Worksheet [worksheetId=" + worksheetId + ", driver=" + driver
				+ ", worksheetStartDate=" + worksheetStartDate
				+ ", worksheetEndDate=" + worksheetEndDate
				+ ", worksheetLines=" + worksheetLines + ", observation="
				+ observation + ", incidence=" + incidence + ", worksheetDate="
				+ worksheetDate + ", worksheetstatus=" + worksheetstatus + "]";
	}	
}
