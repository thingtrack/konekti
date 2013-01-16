package com.thingtrack.konekti.service.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.api.InvoiceLineStatusDao;
import com.thingtrack.konekti.domain.InvoiceLineStatus;
import com.thingtrack.konekti.service.api.InvoiceLineStatusService;

/**
 * @author Thingtrack S.L.
 *
 */
public class InvoiceLineStatusServiceImpl implements InvoiceLineStatusService {
	@Autowired
	private InvoiceLineStatusDao invoiceLineStatusDao;

	@Override
	public List<InvoiceLineStatus> getAll() throws Exception {
		return this.invoiceLineStatusDao.getAll();
		
	}

	@Override
	public InvoiceLineStatus get(Integer feedbackStatusId) throws Exception {
		return this.invoiceLineStatusDao.get(feedbackStatusId);
		
	}

	@Override
	public InvoiceLineStatus getByCode(String code) throws Exception {
		return this.invoiceLineStatusDao.getByCode(code);
		
	}

	@Override
	public InvoiceLineStatus save(InvoiceLineStatus invoiceLineStatus) throws Exception {
		return this.invoiceLineStatusDao.save(invoiceLineStatus);
		
	}

	@Override
	public void delete(InvoiceLineStatus invoiceLineStatus) throws Exception {
		this.invoiceLineStatusDao.delete(invoiceLineStatus);
		
	}

}
