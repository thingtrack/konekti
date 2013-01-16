package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.Iva;

/**
 * @author Thingtrack S.L.
 *
 */
public interface IvaService {
	public List<Iva> getAll() throws Exception;
	public Iva get( Integer ivaId ) throws Exception;
	public Iva getByCode( String code ) throws Exception;
	public Iva save(Iva iva) throws Exception;
	public void delete(Iva iva) throws Exception;
}
