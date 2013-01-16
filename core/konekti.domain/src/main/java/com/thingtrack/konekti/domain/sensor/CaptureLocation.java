package com.thingtrack.konekti.domain.sensor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("LOCATION")
public class CaptureLocation extends Capture {
	@Column(name="ACCURACY")
	private float accuracy;

	@Column(name="ALTITUDE")
	private double altitude;

	@Column(name="BEARING")
	private float bearing;

	@Column(name="LONGITUD")
	private double longitude;

	@Column(name="LATITUD")
	private double latitude;

	@Column(name="PROVIDER")
	private String provider;

	@Column(name="SPEED")
	private float speed;
	
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
