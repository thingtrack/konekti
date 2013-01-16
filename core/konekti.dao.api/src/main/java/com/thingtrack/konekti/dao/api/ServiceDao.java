package com.thingtrack.konekti.dao.api;

import java.util.Date;
import java.util.List;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.EmployeeAgent;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.Service;

/**
 * @author Thingtrack S.L.
 *
 */
public interface ServiceDao extends Dao<Service, Integer> {
	public Service getByCode(String code) throws Exception;
	public List<Service> getAllPlanned(Organization organization) throws Exception;
	public List<Service> getAllPlanned(Organization organization, EmployeeAgent employeeAgent) throws Exception;
	public List<Service> getAllPlanned(Organization organization, EmployeeAgent employeeAgent, Date routeStartDate) throws Exception;
	public List<Service> getAllNonPlanned(Organization organization) throws Exception;
	public List<Service> getAllNonPlanned(Organization organization, EmployeeAgent employeeAgent) throws Exception;
	public List<Service> getAllNonPlanned(Organization organization, Date routeStartDate) throws Exception;
}
