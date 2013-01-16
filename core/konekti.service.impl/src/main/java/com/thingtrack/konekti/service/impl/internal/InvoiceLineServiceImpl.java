package com.thingtrack.konekti.service.impl.internal;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.api.InvoiceLineDao;
import com.thingtrack.konekti.dao.api.InvoiceLineStatusDao;
import com.thingtrack.konekti.domain.Invoice;
import com.thingtrack.konekti.domain.InvoiceLine;
import com.thingtrack.konekti.service.api.InvoiceLineService;

/**
 * @author Thingtrack S.L.
 *
 */
public class InvoiceLineServiceImpl implements InvoiceLineService {
	@Autowired
	private InvoiceLineDao invoiceLineDao;

	@Autowired
	private InvoiceLineStatusDao invoiceLineStatusDao;
	
	@Override
	public List<InvoiceLine> getAll() throws Exception {
		return this.invoiceLineDao.getAll();
		
	}

	@Override
	public InvoiceLine get(Integer feedbackStatusId) throws Exception {
		return this.invoiceLineDao.get(feedbackStatusId);
		
	}

	@Override
	public InvoiceLine getByNumber(Integer number) throws Exception {
		return this.invoiceLineDao.getByNumber(number);
		
	}

	@Override
	public InvoiceLine save(InvoiceLine invoiceLine) throws Exception {
		return this.invoiceLineDao.save(invoiceLine);
		
	}

	@Override
	public void delete(InvoiceLine invoiceLine) throws Exception {
		this.invoiceLineDao.delete(invoiceLine);
		
	}

	@Override
	public InvoiceLine createNewInvoiceLine(Invoice invoice) throws Exception {
		InvoiceLine invoiceLine = new InvoiceLine();
				
		invoiceLine.setInvoice(invoice);
		invoiceLine.setNumber(invoice.getInvoiceLines().size() + 1);
		invoiceLine.setInvoiceLineDate(new Date());
		invoiceLine.setInvoiceLineStatus(invoiceLineStatusDao.getByCode(InvoiceLine.STATUS.OPENED.name()));
		
		return invoiceLine;
	}
}
