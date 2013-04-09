package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.Service;

/**
 * @author Thingtrack S.L.
 *
 */
public interface ServiceService {

	public Service createNewService(Organization organization) throws Exception;
	public List<Service> getAll() throws Exception;
	public List<Service> getAll(Organization currentOrganization) throws Exception;
	public Service get( Integer serviceId ) throws Exception;
	public Service getByCode( String code ) throws Exception;
	public Service save(Service service) throws Exception;
	public void delete(Service service) throws Exception;
}
