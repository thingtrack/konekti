package com.thingtrack.konekti.dao.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.Tax;

/**
 * Tax Data Access LAyer
 * @author Thingtrack S.L.
 *
 */
public interface TaxDao extends Dao<Tax, Integer> {
	
	/**
	 * Obtains {@link Tax} object by its unique code 
	 * @param code  the unique code, not null
	 * @return {@link Tax}
	 * @throws Exception if the code is null
	 */
	public Tax getByCode( String code ) throws Exception;
	
}
