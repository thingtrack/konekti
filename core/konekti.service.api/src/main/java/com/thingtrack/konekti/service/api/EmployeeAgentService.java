package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.Area;
import com.thingtrack.konekti.domain.EmployeeAgent;
import com.thingtrack.konekti.domain.Location;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.User;

/**
 * @author Thingtrack S.L.
 *
 */
public interface EmployeeAgentService {
	
	public List<EmployeeAgent> getAll() throws Exception;
	public EmployeeAgent get(Integer employeeAgentId) throws Exception;
	public EmployeeAgent getByName(Organization organization, String name) throws Exception;
	public EmployeeAgent save(EmployeeAgent employeeAgent) throws Exception;
	public void delete(EmployeeAgent employeeAgent) throws Exception;
	public EmployeeAgent getByUser(User user) throws Exception;
	public List<EmployeeAgent> getDrivers(Organization organizatio) throws Exception;
	public EmployeeAgent getByWorkNumber(Organization organization, String workNumber) throws Exception;
	public List<Location> getAllLocationByOrganization(Organization organization, int agentId) throws Exception;
	public List<Area> getAllAreaByLocation(Location location, int agentId) throws Exception;
}
