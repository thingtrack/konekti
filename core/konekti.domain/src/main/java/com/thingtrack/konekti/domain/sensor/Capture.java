package com.thingtrack.konekti.domain.sensor;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="CAPTURE")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="CAPTURE_TYPE")
public class Capture implements Serializable {
	@Id
	@Column(name="CAPTURE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer captureId;
	
	@Column(name="IDENTIFIER", nullable=false)
	private Integer identifier;
	
	@Column(name="TIME", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date time;
	
	public void setCaptureId(Integer captureId) {
		this.captureId = captureId;
	}

	public Integer getCaptureId() {
		return captureId;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

	public Integer getIdentifier() {
		return identifier;
	}
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((captureId == null) ? 0 : captureId.hashCode());
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
		if (!(obj instanceof Capture))
			return false;
		Capture other = (Capture) obj;
		if (captureId == null) {
			if (other.captureId != null)
				return false;
		} else if (!captureId.equals(other.captureId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Capture [captureId=" + captureId + ", identifier=" + identifier
				+ ", time=" + time + "]";
	}	
	
}
