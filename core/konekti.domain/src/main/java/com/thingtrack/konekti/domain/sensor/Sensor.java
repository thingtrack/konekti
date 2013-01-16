package com.thingtrack.konekti.domain.sensor;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="SENSOR")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="SENSOR_MOD_TYPE")
public class Sensor implements Serializable {
	@Id
	@Column(name="SENSOR_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer sensorId;
	
	@Column(name="CODE", nullable=false, unique=true, length=64)
	private String code;

	@Column(name="DESCRIPTION", length=512)
	private String description;

	@ManyToOne
	@JoinColumn(name="SENSOR_TYPE_ID", nullable=false)
	private SensorType sensorType;
	
	@Column(name="MAC", nullable=false, unique=true, length=64)
	private String mac;
	
	@Column(name="IP", length=64)
	private String ip;
	
	@Column(name="BRAND", length=512)
	private String brand;

	@Column(name="MESSAGE_BROKER_HOST", nullable=false, length=64)
	private String messageBrokerHost;
	
	@Column(name="MESSAGE_BROKER_PORT", nullable=false)
	private int messageBrokerPort=1883;
	
	@Column(name="KEEP_ALIVE", nullable=false)
	private int keepAlive=1200;
	
	@Column(name="TOPIC", nullable=false, length=256)
	private String topic;
	
	@Column(name="QUALITY_OF_SERVICE", nullable=false)
	private int qualityOfService=0;
	
	@ManyToOne
	@JoinColumn(name="SENSOR_STATUS_ID", nullable=false)
	private SensorStatus sensorStatus;

	public enum STATUS {
		ACTIVE,
		UNACTIVE
	}
	
	/**
	 * @return the sensorId
	 */
	public Integer getSensorId() {
		return sensorId;
	}

	/**
	 * @param sensorId the sensorId to set
	 */
	public void setSensorId(Integer sensorId) {
		this.sensorId = sensorId;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the sensorType
	 */
	public SensorType getSensorType() {
		return sensorType;
	}

	/**
	 * @param sensorType the sensorType to set
	 */
	public void setSensorType(SensorType sensorType) {
		this.sensorType = sensorType;
	}

	/**
	 * @return the mac
	 */
	public String getMac() {
		return mac;
	}

	/**
	 * @param mac the mac to set
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @return the messageBrokerHost
	 */
	public String getMessageBrokerHost() {
		return messageBrokerHost;
	}

	/**
	 * @param messageBrokerHost the messageBrokerHost to set
	 */
	public void setMessageBrokerHost(String messageBrokerHost) {
		this.messageBrokerHost = messageBrokerHost;
	}

	/**
	 * @return the messageBrokerPort
	 */
	public int getMessageBrokerPort() {
		return messageBrokerPort;
	}

	/**
	 * @param messageBrokerPort the messageBrokerPort to set
	 */
	public void setMessageBrokerPort(int messageBrokerPort) {
		this.messageBrokerPort = messageBrokerPort;
	}

	/**
	 * @return the keepAlive
	 */
	public int getKeepAlive() {
		return keepAlive;
	}

	/**
	 * @param keepAlive the keepAlive to set
	 */
	public void setKeepAlive(int keepAlive) {
		this.keepAlive = keepAlive;
	}

	/**
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * @param topic the topic to set
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}

	/**
	 * @return the qualityOfService
	 */
	public int getQualityOfService() {
		return qualityOfService;
	}

	/**
	 * @param qualityOfService the qualityOfService to set
	 */
	public void setQualityOfService(int qualityOfService) {
		this.qualityOfService = qualityOfService;
	}
	
	/**
	 * @return the sensorStatus
	 */
	public SensorStatus getSensorStatus() {
		return sensorStatus;
	}

	/**
	 * @param sensorStatus the sensorStatus to set
	 */
	public void setSensorStatus(SensorStatus sensorStatus) {
		this.sensorStatus = sensorStatus;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((sensorId == null) ? 0 : sensorId.hashCode());
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
		if (!(obj instanceof Sensor))
			return false;
		Sensor other = (Sensor) obj;
		if (sensorId == null) {
			if (other.sensorId != null)
				return false;
		} else if (!sensorId.equals(other.sensorId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Sensor [sensorId=" + sensorId + ", code=" + code
				+ ", description=" + description + ", sensorType=" + sensorType
				+ ", mac=" + mac + ", ip=" + ip + ", brand=" + brand
				+ ", messageBrokerHost=" + messageBrokerHost
				+ ", messageBrokerPort=" + messageBrokerPort + ", keepAlive="
				+ keepAlive + ", topic=" + topic + ", qualityOfService="
				+ qualityOfService + ", sensorStatus=" + sensorStatus + "]";
	}
	
}
