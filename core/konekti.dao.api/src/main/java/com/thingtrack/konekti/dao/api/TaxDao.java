package com.thingtrack.konekti.dao.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.Tax;

/**
 * @author Thingtrack S.L.
 *
 */
public interface TaxDao extends Dao<Tax, Integer> {
	public Tax getByCode( String code ) throws Exception;
	
}
