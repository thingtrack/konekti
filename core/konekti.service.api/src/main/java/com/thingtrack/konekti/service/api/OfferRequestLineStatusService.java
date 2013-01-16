package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.OfferRequestLineStatus;

/**
 * @author Thingtrack S.L.
 *
 */
public interface OfferRequestLineStatusService {
	public List<OfferRequestLineStatus> getAll() throws Exception;
	public OfferRequestLineStatus get( Integer offerRequestLineStatusId ) throws Exception;
	public OfferRequestLineStatus getByCode( String code ) throws Exception;
	public OfferRequestLineStatus save(OfferRequestLineStatus offerRequestLineStatus) throws Exception;
	public void delete(OfferRequestLineStatus offerRequestLineStatus) throws Exception;
}
