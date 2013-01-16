package com.thingtrack.konekti.domain.sensor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("MESSAGE")
public class CaptureMessage extends Capture {
	@Column(name="IDENTIFIED_ID")
	private String identifieId;
	
	@Column(name="MESSAGE")
	private String message;

	/**
	 * @param identifieId the identifieId to set
	 */
	public void setIdentifieId(String identifieId) {
		this.identifieId = identifieId;
	}

	/**
	 * @return the identifieId
	 */
	public String getIdentifieId() {
		return identifieId;
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
}
