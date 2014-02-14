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
 * {@link EmployeeAgent} Data Access Layer
 * <p>
 * @author Thingtrack S.L.
 *
 */
public interface EmployeeAgentDao extends Dao<EmployeeAgent, Integer> {
	
	/**
	 * Obtains all {@link EmployeeAgent employee Agents} associated to a particular {@code User}
	 *  
	 * @param user  the user account associate the collection, not null
  	 * @return the collection associated to the user
	 * @throws Exception if the user passed is null
	 */
	public List<EmployeeAgent> getAll(User user) throws Exception;
	
	/**
	 * Obtains the {@link EmployeeAgent} which belongs to the {@link Organization} and has the passed {@code name}
	 * 
	 * @param organization The organization belong to, not null
	 * @param name, the name of the this
	 * @return the queried {@code EmployeeAgent}
	 * @throws Exception if there no result by the query
	 */
	public EmployeeAgent getByName(Organization organization, String name) throws Exception;
	
	/**
	 * Obtains the {@link EmployeeAgent} associated the the user account 
	 * @param user  the user account 
	 * @return the {@code EmployeeAgent}
	 * @throws Exception if there is no result obtained
	 */
	public EmployeeAgent getByUser(User user) throws Exception;
	
	/**
	 * Obtains the collection of {@link EmployeeAgent} which belongs to the {@link Organization} and has the passed {@code EmployeeAgentType}
	 * 
	 * @param organization The organization belong to, not null
	 * @param employeeAgentType, the type of the this
	 * @return the queried collection of {@code EmployeeAgent}
	 * @throws Exception if there no result by the query
	 */
	public List<EmployeeAgent> getByType(Organization organization, EmployeeAgentType employeeAgentType) throws Exception;
	
	/**
	 * Obtains the {@link EmployeeAgent} which belongs to the {@link Organization} and has the passed {@code workNumber}
	 * 
	 * @param organization The organization belong to, not null
	 * @param workNumber, the unique work number in the organizations, not null
	 * @return the queried {@code EmployeeAgent}
	 * @throws Exception if there no result by the query
	 */
	public EmployeeAgent getByWorkNumber(Organization organization, String workNumber) throws Exception;
	
	/**
	 * Obtains the collection of {@link Location} which belongs to the {@link Organization} and agent identifier
	 * 
	 * @param organization The organization belong to, not null
	 * @param agentId, the unique identifier of the employee agent, not null
	 * @return the queried collection of {@code EmployeeAgent}
	 * @throws Exception if there no result by the query
	 */
	public List<Location> getAllLocationByOrganization(Organization organization, int agentId) throws Exception;
	
	/**
	 * Obtains the {@link Area} which belongs to the {@link Location} and has the passed an agent identifier
	 * 
	 * @param organization The organization belong to, not null
	 * @param agentID, the unique identifier of the employee agent, not null
	 * @return the queried {@code EmployeeAgent}
	 * @throws Exception if there no result by the query
	 */
	public List<Area> getAllAreaByLocation(Location location, int agentId) throws Exception;
	 
}
