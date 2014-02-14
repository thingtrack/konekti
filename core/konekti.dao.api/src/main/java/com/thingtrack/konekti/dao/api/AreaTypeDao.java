package com.thingtrack.konekti.dao.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.AreaType;

/**
 * AreaType Data Access Layer
 * <p>
 * @author Thingtrack S.L.
 *
 */
public interface AreaTypeDao extends Dao<AreaType, Integer> {
	
	/**
	 * Obtains an {@link AreaType} object found by its {@code name}
	 * 
	 * @param name  the name of the {@code AreaType}, not null
	 * @return an {@code AreaType} object, not null
	 * @throws Exception if there is no {@code AreaType} associated to the give {@code name}
	 */
	public AreaType getByName(String name) throws Exception;
	
}
