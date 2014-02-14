package com.thingtrack.konekti.dao.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.InvoiceLineStatus;

/**
 * @deprecated
 * @author Thingtrack S.L.
 *
 */
public interface InvoiceLineStatusDao extends Dao<InvoiceLineStatus, Integer> {
	public InvoiceLineStatus getByCode(String code) throws Exception;

}
