package com.thingtrack.konekti.service.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.api.EmployeeAgentDao;
import com.thingtrack.konekti.domain.Area;
import com.thingtrack.konekti.domain.EmployeeAgent;
import com.thingtrack.konekti.domain.EmployeeAgentType;
import com.thingtrack.konekti.domain.Location;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.User;
import com.thingtrack.konekti.service.api.EmployeeAgentService;
import com.thingtrack.konekti.service.api.EmployeeAgentTypeService;

public class EmployeeAgentServiceImpl implements EmployeeAgentService {
	
	
	@Autowired
	private EmployeeAgentDao employeeAgentDao;
	
	@Autowired 
	private EmployeeAgentTypeService employeeAgentTypeService;
	
	@Override
	public List<EmployeeAgent> getAll() throws Exception {
		//TODO: Pass the Organization to DAO
		return this.employeeAgentDao.getAll();
		
	}

	@Override
	public EmployeeAgent get(Integer employeeAgentId) throws Exception {
		return this.employeeAgentDao.get(employeeAgentId);
		
	}

	@Override
	public EmployeeAgent getByName(Organization organization, String name) throws Exception {
		return this.employeeAgentDao.getByName(organization, name);
		
	}

	@Override
	public EmployeeAgent save(EmployeeAgent employeeAgent) throws Exception {
		return this.employeeAgentDao.save(employeeAgent);
		
	}

	@Override
	public void delete(EmployeeAgent employeeAgent) throws Exception {
		this.employeeAgentDao.delete(employeeAgent);
		
	}

	@Override
	public EmployeeAgent getByUser(User user) throws Exception {
		return this.employeeAgentDao.getByUser(user);
		
	}

	@Override
	public List<EmployeeAgent> getDrivers(Organization organization) throws Exception {

		EmployeeAgentType employeeAgentType = employeeAgentTypeService.getByName(EmployeeAgent.EMPLOYEE_AGENT_TYPE.OPERATOR_TYPE_1.name());
		
		return this.employeeAgentDao.getByType(organization, employeeAgentType);

	}

	@Override
	public EmployeeAgent getByWorkNumber(Organization organization, String workNumber) throws Exception {
		return this.employeeAgentDao.getByWorkNumber(organization, workNumber);
		
	}

	@Override
	public List<Location> getAllLocationByOrganization(Organization organization, int agentId) throws Exception {
		return this.employeeAgentDao.getAllLocationByOrganization(organization, agentId);
	}
	
	@Override
	public List<Area> getAllAreaByLocation(Location location, int agentId) throws Exception {
		return this.employeeAgentDao.getAllAreaByLocation(location, agentId);
	}
}
