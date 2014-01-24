package com.thingtrack.konekti.dao.api;

import java.util.List;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.Application;
import com.thingtrack.konekti.domain.User;

/**
 * Application Data Access Layer
 * <p>
 * @author Thingtrack S.L.
 *
 */
public interface ApplicationDao extends Dao<Application, Integer> {
	
	/**
	 * Obtains an {@link Application} object found by its {@code name}
	 * 
	 * @param name  the name of the {@code Application}, not null
	 * @return an {@code Application} object, not null
	 * @throws Exception if there is no {@code Application} associated to the give {@code code}
	 */
	public Application getByName(String name) throws Exception;
	
	/**
	 * 
	 * Obtains an {@link List<Application>} collections associated to the {@link User}
	 * 
	 * @param user the user to obtain the {@code Applications} associated, not null
	 * @return
	 * @throws Exception
	 */
	public List<Application> getAll(User user) throws Exception;
}