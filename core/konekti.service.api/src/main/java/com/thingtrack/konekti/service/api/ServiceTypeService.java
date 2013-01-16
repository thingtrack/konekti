package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.ServiceType;

/**
 * @author Thingtrack S.L.
 *
 */
public interface ServiceTypeService {
	public List<ServiceType> getAll() throws Exception;
	public ServiceType get( Integer serviceTypeId ) throws Exception;
	public ServiceType getByCode( String code ) throws Exception;
	public ServiceType save(ServiceType serviceType) throws Exception;
	public void delete(ServiceType serviceType) throws Exception;
}
