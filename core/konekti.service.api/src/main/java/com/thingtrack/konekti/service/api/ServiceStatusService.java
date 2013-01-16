package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.ServiceStatus;

/**
 * @author Thingtrack S.L.
 *
 */
public interface ServiceStatusService {
	public List<ServiceStatus> getAll() throws Exception;
	public ServiceStatus get( Integer serviceStatusId ) throws Exception;
	public ServiceStatus getByCode( String code ) throws Exception;
	public ServiceStatus save(ServiceStatus serviceStatus) throws Exception;
	public void delete(ServiceStatus serviceStatus) throws Exception;
}
