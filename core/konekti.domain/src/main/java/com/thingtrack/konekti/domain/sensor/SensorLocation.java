package com.thingtrack.konekti.domain.sensor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("LOCATION")
public class SensorLocation extends Sensor {
	@Column(name="MIN_TIME", nullable=false)
	private long minTime;
	
	@Column(name="MIN_DISTANCE", nullable=false)
	private float minDistance;

	/**
	 * @return the minTime
	 */
	public long getMinTime() {
		return minTime;
	}

	/**
	 * @param minTime the minTime to set
	 */
	public void setMinTime(long minTime) {
		this.minTime = minTime;
	}

	/**
	 * @return the minDistance
	 */
	public float getMinDistance() {
		return minDistance;
	}

	/**
	 * @param minDistance the minDistance to set
	 */
	public void setMinDistance(float minDistance) {
		this.minDistance = minDistance;
	}
}
