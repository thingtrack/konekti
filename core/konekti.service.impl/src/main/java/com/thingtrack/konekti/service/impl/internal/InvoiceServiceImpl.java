/*
 * Copyright 2011 Thingtrack, S.L.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.thingtrack.konekti.service.impl.internal;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.domain.Invoice;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.Sequence;
import com.thingtrack.konekti.dao.api.InvoiceDao;
import com.thingtrack.konekti.dao.api.InvoiceStatusDao;
import com.thingtrack.konekti.service.api.InvoiceService;
import com.thingtrack.konekti.service.api.SequenceService;

/**
 * @author Thingtrack S.L.
 *
 */
public class InvoiceServiceImpl implements InvoiceService {
	@Autowired
	private InvoiceDao invoiceDao;

	@Autowired
	private InvoiceStatusDao invoiceStatusDao;
	
	@Autowired
	private SequenceService sequenceService;
	
	@Override
	public List<Invoice> getAll() throws Exception {
		return this.invoiceDao.getAll();
		
	}

	@Override
	public Invoice get(Integer feedbackStatusId) throws Exception {
		return this.invoiceDao.get(feedbackStatusId);
		
	}

	@Override
	public Invoice getByCode(String code) throws Exception {
		return this.invoiceDao.getByCode(code);
		
	}

	@Override
	public Invoice save(Invoice invoice) throws Exception {
		return this.invoiceDao.save(invoice);
		
	}

	@Override
	public void delete(Invoice invoice) throws Exception {
		this.invoiceDao.delete(invoice);

	}

	@Override
	public Invoice createNewInvoice(Organization organization) throws Exception {
		Invoice invoice = new Invoice();
		
		invoice.setCode(sequenceService.setNextSequence(Sequence.CODE.INVOICE.name()));
		invoice.setOrganization(organization);
		invoice.setInvoiceDate(new Date());
		invoice.setInvoiceStatus(invoiceStatusDao.getByCode(Invoice.STATUS.OPENED.name()));
		
		return invoice;
	}
}
