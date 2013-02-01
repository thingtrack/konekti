package com.thingtrack.konekti.domain.mobile.device;

public class Context {
	private int organizationId;
	private int userId;
	private int vehicleId;
	private int routeId;
	
	public Context() {
		
	}
	
	public Context(int organizationId, int userId, int vehicleId, int routeId) {
		this.organizationId = organizationId;
		this.userId = userId;
		this.vehicleId = vehicleId;
		this.routeId = routeId;
		
	}
	
	/**
	 * @return the organizationId
	 */
	public int getOrganizationId() {
		return organizationId;
	}
	/**
	 * @param organizationId the organizationId to set
	 */
	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the vehicleId
	 */
	public int getVehicleId() {
		return vehicleId;
	}
	/**
	 * @param vehicleId the vehicleId to set
	 */
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	/**
	 * @param routeId the routeId to set
	 */
	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	/**
	 * @return the routeId
	 */
	public int getRouteId() {
		return routeId;
	}
	
	
}
