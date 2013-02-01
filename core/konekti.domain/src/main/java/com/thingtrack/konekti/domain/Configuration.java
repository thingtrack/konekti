package com.thingtrack.konekti.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * @author Thingtrack S.L.
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "CONFIGURATION")
public class Configuration implements Serializable {
	@Id
	@Column(name = "CONFIGURATION_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer configurationId;
	
	@Column(name = "CODE", nullable = false, unique = true, length = 45)
	private String code;
	
	@Column(name = "NAME", nullable = false, length = 64)
	private String name;
	
	@Column(name = "VERSION", nullable = false, length = 64)
	private String version;

	@Column(name = "FAVICON")
	@Lob
	private byte[] favicon;
	
	@Column(name = "LOGO_INIT")
	@Lob
	private byte[] logoInit;

	@Column(name = "LOGO_WORKBENCH")
	@Lob
	private byte[] logoWorkbench;

	/**
	 * @return the configurationId
	 */
	public Integer getConfigurationId() {
		return configurationId;
	}

	/**
	 * @param configurationId the configurationId to set
	 */
	public void setConfigurationId(Integer configurationId) {
		this.configurationId = configurationId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the logoInit
	 */
	public byte[] getLogoInit() {
		return logoInit;
	}

	/**
	 * @param logoInit the logoInit to set
	 */
	public void setLogoInit(byte[] logoInit) {
		this.logoInit = logoInit;
	}

	/**
	 * @return the logoWorkbench
	 */
	public byte[] getLogoWorkbench() {
		return logoWorkbench;
	}

	/**
	 * @param logoWorkbench the logoWorkbench to set
	 */
	public void setLogoWorkbench(byte[] logoWorkbench) {
		this.logoWorkbench = logoWorkbench;
	}

	/**
	 * @return the favicon
	 */
	public byte[] getFavicon() {
		return favicon;
	}

	/**
	 * @param favicon the favicon to set
	 */
	public void setFavicon(byte[] favicon) {
		this.favicon = favicon;
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
}
