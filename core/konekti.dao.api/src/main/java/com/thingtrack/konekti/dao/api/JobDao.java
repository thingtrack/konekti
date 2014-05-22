package com.thingtrack.konekti.dao.api;

import java.util.List;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.Job;
import com.thingtrack.konekti.domain.User;

/**
 * Job Data Access Layer
 * <p>
 * @author carlos
 *
 */
public interface JobDao extends Dao<Job, Integer> {
	/**
	 * Get all {@link Job} associated to a {@link User}
	 * 
	 * @param user  the user associated to that job, not null
	 * @return 
	 * @throws Exception
	 */
	public List<Job> getAll(User user) throws Exception;
	
	/**
	 * Obtains the {@link Job} associated to a particular {@code group} and {@code name}
	 * @param group  not null
	 * @param name  not null
	 * @return {@link Job} by its name and group
	 * @throws Exception if there is no group associated
	 */
	public List<Job> getByGroupName(String group, String name) throws Exception;
	
	public Job getByGroupNameAndArea(Integer AreaId, String group, String name) throws Exception;
}
