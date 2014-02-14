package com.thingtrack.konekti.dao.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.OfferLine;

/**
 * @deprecated
 * @author Thingtrack S.L.
 *
 */
public interface OfferLineDao extends Dao<OfferLine, Integer> {
	public OfferLine getByNumber(Integer number) throws Exception;
	
}
