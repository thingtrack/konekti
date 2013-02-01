package com.thingtrack.konekti.domain.mobile.config;

public class Configure {
	protected boolean active = true;
	protected String deviceName;
	protected String mac;
	protected String host;
	protected int port;
	protected int keepAlive;
	protected int qualityOfService;
	protected String topic;
	
	public Configure() {
		
	}
	
	public Configure(boolean active, String deviceName, String mac, String host, int port, int keepAlive, int qualityOfService, String topic) {
		this.active = active;
		this.deviceName = deviceName;
		this.mac = mac;
		this.host = host;
		this.port = port;
		this.keepAlive = keepAlive;
		this.qualityOfService = qualityOfService;
		this.topic = topic;
		
	}
	
	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @return the mac
	 */
	public String getMac() {
		return mac;
	}

	/**
	 * @param deviceName the deviceName to set
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	/**
	 * @return the deviceName
	 */
	public String getDeviceName() {
		return deviceName;
	}
	
	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @return the keepAlive
	 */
	public int getKeepAlive() {
		return keepAlive;
	}

	/**
	 * @return the qualityOfService
	 */
	public int getQualityOfService() {
		return qualityOfService;
	}

	/**
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}
	
}
