package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.Invoice;
import com.thingtrack.konekti.domain.InvoiceLine;

/**
 * @author Thingtrack S.L.
 *
 */
public interface InvoiceLineService {
	public List<InvoiceLine> getAll() throws Exception;
	public InvoiceLine get( Integer feedbackStatusId ) throws Exception;
	public InvoiceLine getByNumber( Integer number ) throws Exception;
	public InvoiceLine save(InvoiceLine invoiceLine) throws Exception;
	public void delete(InvoiceLine invoiceLine) throws Exception;
	public InvoiceLine createNewInvoiceLine(Invoice invoice) throws Exception;
}
