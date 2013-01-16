package com.thingtrack.konekti.dao.api;

import java.util.List;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.OfferRequestLine;
import com.thingtrack.konekti.domain.OfferRequestLineStatus;


/**
 * @author Thingtrack S.L.
 *
 */
public interface OfferRequestLineDao extends Dao<OfferRequestLine, Integer> {
	public OfferRequestLine getByNumber(Integer number) throws Exception;
	public List<OfferRequestLine> getByStatus(OfferRequestLineStatus offerRequestLineStatus) throws Exception;
}
