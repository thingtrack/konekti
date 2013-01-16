package com.thingtrack.konekti.dao.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.OfferLineStatus;

/**
 * @author Thingtrack S.L.
 *
 */
public interface OfferLineStatusDao extends Dao<OfferLineStatus, Integer> {
	public OfferLineStatus getByCode(String code) throws Exception;

}
