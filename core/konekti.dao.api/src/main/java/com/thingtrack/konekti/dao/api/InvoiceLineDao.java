package com.thingtrack.konekti.dao.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.InvoiceLine;

/**
 * @author Thingtrack S.L.
 *
 */
public interface InvoiceLineDao extends Dao<InvoiceLine, Integer> {
	public InvoiceLine getByNumber(Integer number) throws Exception;
	
}
