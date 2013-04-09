package com.thingtrack.konekti.dao.api;

import java.util.List;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.Area;
import com.thingtrack.konekti.domain.EmployeeAgent;
import com.thingtrack.konekti.domain.EmployeeAgentType;
import com.thingtrack.konekti.domain.Location;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.User;

/**
 * @author Thingtrack S.L.
 *
 */
public interface EmployeeAgentDao extends Dao<EmployeeAgent, Integer> {
	
	public EmployeeAgent getByName(Organization organization, String name) throws Exception;
	public EmployeeAgent getByUser(User user) throws Exception;
	public List<EmployeeAgent> getByType(Organization organization, EmployeeAgentType employeeAgentType) throws Exception;
	public EmployeeAgent getByWorkNumber(Organization organization, String workNumber) throws Exception;
	public List<Location> getAllLocationByOrganization(Organization organization, int agentId) throws Exception;
	public List<Area> getAllAreaByLocation(Location location, int agentId) throws Exception;
	 
}
