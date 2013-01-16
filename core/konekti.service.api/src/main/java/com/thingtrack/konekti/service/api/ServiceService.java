package com.thingtrack.konekti.service.api;

import java.util.Date;
import java.util.List;

import com.thingtrack.konekti.domain.EmployeeAgent;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.Service;

/**
 * @author Thingtrack S.L.
 *
 */
public interface ServiceService {
	public List<Service> getAll() throws Exception;
	public Service get( Integer serviceId ) throws Exception;
	public Service getByCode( String code ) throws Exception;
	public Service save(Service service) throws Exception;
	public void delete(Service service) throws Exception;
	public Service createNewService(Organization organization) throws Exception;
	public List<Service> getAllPlanned(Organization organization) throws Exception;
	public List<Service> getAllPlanned(Organization organization, EmployeeAgent employeeAgent) throws Exception;
	public List<Service> getAllPlanned(Organization organization, EmployeeAgent employeeAgent, Date routeStartDate) throws Exception;
	public List<Service> getAllNonPlanned(Organization organization) throws Exception;
	public List<Service> getAllNonPlanned(Organization organization, EmployeeAgent employeeAgent) throws Exception;
	public List<Service> getAllNonPlanned(Organization organization, Date routeStartDate) throws Exception;
	
	public List<Service> getCandidatesForAssignment(Organization organization, EmployeeAgent employeeAgent, Date routeStartDate) throws Exception;
}
