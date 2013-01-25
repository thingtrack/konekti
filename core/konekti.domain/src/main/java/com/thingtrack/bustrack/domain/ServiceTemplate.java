package com.thingtrack.bustrack.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.thingtrack.konekti.domain.Service;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="SERVICE_TEMPLATE")
public class ServiceTemplate implements Serializable {
	@Id
	@Column(name="SERVICE_TEMPLATE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer serviceTemplateId;
	
	@Column(name="CODE", nullable=false, unique=true, length=256)
	@Size(min=1, max=256)
	@NotNull
	private String code;
	
	@Column(name="DESCRIPTION", length=1024)
	@Size(min=1, max=1024)
	private String description;
	
	@Column(name="FILE")
	@Lob
	private byte[] file;

	@OneToMany(mappedBy="serviceTemplate",fetch = FetchType.LAZY)
	private List<Service> services = new ArrayList<Service>();
	
	/**
	 * @return the serviceTemplateId
	 */
	public Integer getServiceTemplateId() {
		return serviceTemplateId;
	}

	/**
	 * @param serviceTemplateId the serviceTemplateId to set
	 */
	public void setServiceTemplateId(Integer serviceTemplateId) {
		this.serviceTemplateId = serviceTemplateId;
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
	 * @return the file
	 */
	public byte[] getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(byte[] file) {
		this.file = file;
	}

	/**
	 * @return the services
	 */
	public List<Service> getServices() {
		return services;
	}

	/**
	 * @param services the services to set
	 */
	public void setServices(List<Service> services) {
		this.services = services;
	}
}
