package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.InvoiceLineStatus;

/**
 * @author Thingtrack S.L.
 *
 */
public interface InvoiceLineStatusService {
	public List<InvoiceLineStatus> getAll() throws Exception;
	public InvoiceLineStatus get( Integer feedbackStatusId ) throws Exception;
	public InvoiceLineStatus getByCode( String code ) throws Exception;
	public InvoiceLineStatus save(InvoiceLineStatus invoiceLineStatus) throws Exception;
	public void delete(InvoiceLineStatus invoiceLineStatus) throws Exception;
}
