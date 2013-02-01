package com.thingtrack.konekti.domain.mobile.device;

import org.simpleframework.xml.Element;

public class MessageCaptureDevice extends CaptureDevice {
	public static final String TOPIC_BASE = "com/thingtrack/konekti/sensor/message/mobile";
	
	@Element
	private int identifier;
	
	@Element
	private String message;

	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}

	/**
	 * @return the identifier
	 */
	public int getIdentifier() {
		return identifier;
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
