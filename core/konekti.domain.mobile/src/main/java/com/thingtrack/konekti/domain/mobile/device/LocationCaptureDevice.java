package com.thingtrack.konekti.domain.mobile.device;

import org.simpleframework.xml.Element;

public class LocationCaptureDevice extends CaptureDevice {

	public static final String TOPIC_BASE = "com/thingtrack/konekti/sensor/location/mobile";
	
	@Element
	private int identifier;
	@Element
	private float accuracy;
	@Element
	private double altitude;
	@Element
	private float bearing;
	@Element
	private double longitude;
	@Element
	private double latitude;
	@Element
	private String provider;
	@Element
	private float speed;

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}

	public int getIdentifier() {
		return identifier;
	}
	
	public float getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	public float getBearing() {
		return bearing;
	}

	public void setBearing(float bearing) {
		this.bearing = bearing;
	}
	
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

}
