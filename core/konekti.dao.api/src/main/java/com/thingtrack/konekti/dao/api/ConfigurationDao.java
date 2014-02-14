package com.thingtrack.konekti.dao.api;

import java.util.List;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.Configuration;
import com.thingtrack.konekti.domain.MenuResource;
import com.thingtrack.konekti.domain.User;

/**
 * {@link Configuration} Data Access Layer
 * <p>
 * @author Thingtrack S.L.
 *
 */
public interface ConfigurationDao extends Dao<Configuration, Integer> {
	
	/**
	 * Obtains an {@link Configuration} by its tag
	 * 
	 * @param tag  the unique tag to filter the Configuration, not null
	 * @return {@code Configuration} which the passed {@code tag} belongs to
	 * @throws Exception if the the {@code tag} no belongs to any {@code Configuration} or it is null
	 */
	public Configuration getByTag(String tag) throws Exception;
	
	/**
	 * Obtains an {@link List<Configurtion>} being associated to a {@link User}
	 * 
	 * @param user  the unique user to filter the Configuration collection, not null
	 * @return {@code Configuration} collections which the passed {@code User} belongs to
	 * @throws Exception if the the {@code User} no belongs to any {@code Configuration} or it is null
	 */
	public List<Configuration> getAll(User user) throws Exception;
	
	/**
	 * Obtains an {@link Configuration} by its code
	 * 
	 * @param code  the unique code to filter the ClientType, not null
	 * @param tag  the unique code to filter the ClientType, not null
	 * @param menuResource  the unique code to filter the ClientType, not null
	 * @return {@code ClientType} which the passed {@code code} belongs to
	 * @throws Exception if the the {@code code} no belongs to any {@code ClientType} or it is null
	 */
	public Configuration getByTag(User user, String tag, MenuResource menuResource) throws Exception;
}
