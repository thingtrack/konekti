package com.thingtrack.konekti.dao.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.OfferRequestLineStatus;

/**
 * @deprecated
 * @author Thingtrack S.L.
 *
 */
public interface OfferRequestLineStatusDao extends Dao<OfferRequestLineStatus, Integer> {
	public OfferRequestLineStatus getByCode(String code) throws Exception;
	
}
