package com.thingtrack.konekti.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name="APPLICATION")
public class Application implements Serializable {
	@Id
	@Column(name="APPLICATION_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer applicationId;
	
	@Column(name="NAME", nullable=false, unique=true, length=64)
	@Size(min=1, max=64)
	@NotNull
	private String name;
	
	@Column(name="DESCRIPTION", length=256)
	@Size(min=1, max=256)
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "ORGANIZATION_ID", nullable = false)
	private Organization organization;
	
	@Column(name="APPLICATION_TYPE_ID", length=256)
	@Size(min=1, max=256)
	@Enumerated(EnumType.STRING)
	private TYPE applicationType;

	public enum TYPE {
		Web,
		Nativa,
		Hibrida
		
	}
	
	/**
	 * @return the applicationId
	 */
	public Integer getApplicationId() {
		return applicationId;
	}

	/**
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
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
	 * @return the applicationType
	 */
	public TYPE getApplicationType() {
		return applicationType;
	}

	/**
	 * @param applicationType the applicationType to set
	 */
	public void setApplicationType(TYPE applicationType) {
		this.applicationType = applicationType;
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
}
