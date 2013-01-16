package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.OfferLineStatus;

/**
 * @author Thingtrack S.L.
 *
 */
public interface OfferLineStatusService {
	public List<OfferLineStatus> getAll() throws Exception;
	public OfferLineStatus get( Integer offerLineId ) throws Exception;
	public OfferLineStatus getByCode( String code ) throws Exception;
	public OfferLineStatus save(OfferLineStatus offerLineStatus) throws Exception;
	public void delete(OfferLineStatus offerLineStatus) throws Exception;
}
