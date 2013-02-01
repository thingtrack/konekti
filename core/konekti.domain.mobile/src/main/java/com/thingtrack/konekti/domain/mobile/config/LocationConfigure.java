package com.thingtrack.konekti.domain.mobile.config;

public class LocationConfigure extends Configure {
	private long minTime;
	private float minDistance;
		
	public LocationConfigure(boolean active, String deviceName, long minTime, float minDistance, String mac, String host, int port, int keepAlive, int qualityOfService, String topic) {
		super(active, deviceName, mac, host, port, keepAlive, qualityOfService, topic);
		this.minTime = minTime;
		this.minDistance = minDistance;

	}
	
	/**
	 * @return the minTime
	 */
	public long getMinTime() {
		return minTime;
	}

	/**
	 * @return the minDistance
	 */
	public float getMinDistance() {
		return minDistance;
	}
	
	public void setMinTime(long minTime) {
		this.minTime = minTime;
		
	}
	
	public void setMinDistance(float minDistance) {
		this.minDistance = minDistance;
		
	}
}
