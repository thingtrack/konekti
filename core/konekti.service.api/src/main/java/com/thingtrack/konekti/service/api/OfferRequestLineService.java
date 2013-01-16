package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.OfferRequestLine;

/**
 * @author Thingtrack S.L.
 *
 */
public interface OfferRequestLineService {
	public List<OfferRequestLine> getAll() throws Exception;
	public OfferRequestLine get( Integer offerLineId ) throws Exception;
	public OfferRequestLine getByNumber( Integer number ) throws Exception;
	public OfferRequestLine save(OfferRequestLine offerRequestLine) throws Exception;
	public void delete(OfferRequestLine offerRequestLine) throws Exception;
}
