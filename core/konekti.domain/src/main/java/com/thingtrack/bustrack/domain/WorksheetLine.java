package com.thingtrack.bustrack.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.thingtrack.konekti.domain.Service;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="WORKSHEET_LINE")
public class WorksheetLine implements Serializable {
	@Id
	@Column(name="WORKSHEET_LINE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer worksheetLineId;
	
	@Column(name="NUMBER",  nullable=false)
	private Integer number;
	
	@ManyToOne
	@JoinColumn(name="WORKSHEET_ID", nullable=false)
	private Worksheet worksheet;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SERVICE_ID")
	private Service service;
	
	@Column(name="COMMENT", length=512)
	private String comment;
	
	@Column(name="WORKSHEET_DATE", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date worksheetDate=new Date();

	/**
	 * @return the worksheetLineId
	 */
	public Integer getWorksheetLineId() {
		return worksheetLineId;
	}

	/**
	 * @param worksheetLineId the worksheetLineId to set
	 */
	public void setWorksheetLineId(Integer worksheetLineId) {
		this.worksheetLineId = worksheetLineId;
	}

	/**
	 * @return the worksheet
	 */
	public Worksheet getWorksheet() {
		return worksheet;
	}

	/**
	 * @param worksheet the worksheet to set
	 */
	public void setWorksheet(Worksheet worksheet) {
		
		if(this.worksheet != null)
			this.worksheet.removeWorksheetLine(this);
		
		this.worksheet = worksheet;
	}

	/**
	 * @return the service
	 */
	public Service getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */
	public void setService(Service service) {
		this.service = service;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
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
	 * @param number the number to set
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * @return the number
	 */
	public Integer getNumber() {
		return number;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((worksheetLineId == null) ? 0 : worksheetLineId.hashCode());
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
		if (!(obj instanceof WorksheetLine))
			return false;
		WorksheetLine other = (WorksheetLine) obj;
		if (worksheetLineId == null) {
			if (other.worksheetLineId != null)
				return false;
		} else if (!worksheetLineId.equals(other.worksheetLineId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WorksheetLine [worksheetLineId=" + worksheetLineId
				+ ", number=" + number + ", worksheet=" + worksheet
				+ ", service=" + service + ", comment=" + comment
				+ ", worksheetDate=" + worksheetDate + "]";
	}
}
