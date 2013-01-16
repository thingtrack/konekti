package com.thingtrack.bustrack.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Thingtrack S.L.
 *
 */
@Entity
@Table(name="ROUTE_STATUS")
public class RouteStatus {
	@Id
	@Column(name="ROUTE_STATUS_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer routeStatusId;
	
	@Column(name="CODE", nullable=false, unique=true, length=64)
	@Size(min=1, max=64)
	@NotNull
	private String code;
	
	@Column(name="DESCRIPTION", length=512)
	@Size(min=1, max=1024)
	private String description;

	public enum STATUS {
		PENDING,
		RUNNING,
		FINISH
	}
	
	/**
	 * @param routeStatusId the routeStatusId to set
	 */
	public void setRouteStatusId(Integer routeStatusId) {
		this.routeStatusId = routeStatusId;
	}

	/**
	 * @return the routeStatusId
	 */
	public Integer getRouteStatusId() {
		return routeStatusId;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
}
