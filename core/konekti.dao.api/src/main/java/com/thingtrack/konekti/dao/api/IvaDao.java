package com.thingtrack.konekti.dao.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.Iva;

/**
 * @author Thingtrack S.L.
 *
 */
public interface IvaDao extends Dao<Iva, Integer> {
	public Iva getByCode( String code ) throws Exception;
	
}
