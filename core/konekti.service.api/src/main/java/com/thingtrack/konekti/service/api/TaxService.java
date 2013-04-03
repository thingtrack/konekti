package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.Tax;

/**
 * @author Thingtrack S.L.
 *
 */
public interface TaxService {
	public List<Tax> getAll() throws Exception;
	public Tax get( Integer taxId ) throws Exception;
	public Tax getByCode( String code ) throws Exception;
	public Tax save(Tax tax) throws Exception;
	public void delete(Tax tax) throws Exception;
}
