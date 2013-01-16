package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.Offer;
import com.thingtrack.konekti.domain.OfferLine;

/**
 * @author Thingtrack S.L.
 *
 */
public interface OfferLineService {
	public List<OfferLine> getAll() throws Exception;
	public OfferLine get( Integer offerLineId ) throws Exception;
	public OfferLine getByNumber( Integer number ) throws Exception;
	public OfferLine save(OfferLine offerLine) throws Exception;
	public void delete(OfferLine offerLine) throws Exception;
	public OfferLine createNewOfferLine(Offer offer) throws Exception;
}
