package com.thingtrack.konekti.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="REPORT")
public class Report implements Serializable {
	@Id
	@Column(name="REPORT_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer reportId;

	@ManyToOne
	@JoinColumn(name="ORGANIZATION_ID")	
	private Organization organization;
	
	@Column(name="CODE", unique=true, nullable=false, length=64)
	private String code;
	
	@Column(name="DESCRIPTION", length=512)
	private String description;
	
	@Column(name="TEMPLATE")	
	@Lob
	private byte[] template;
	
	@Column(name="ACTIVE", nullable=false)
	private boolean active=true;

	/**
	 * @return the reportId
	 */
	public Integer getReportId() {
		return reportId;
	}

	/**
	 * @param reportId the reportId to set
	 */
	public void setReportId(Integer reportId) {
		this.reportId = reportId;
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
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the organization
	 */
	public Organization getOrganization() {
		return organization;
	}

	/**
	 * @param organization the organization to set
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
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
	 * @return the template
	 */
	public byte[] getTemplate() {
		return template;
	}

	/**
	 * @param template the template to set
	 */
	public void setTemplate(byte[] template) {
		this.template = template;
	}
}
