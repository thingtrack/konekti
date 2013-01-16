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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.domain.InvoiceStatus;
import com.thingtrack.konekti.dao.api.InvoiceStatusDao;
import com.thingtrack.konekti.service.api.InvoiceStatusService;

/**
 * @author Thingtrack S.L.
 *
 */
public class InvoiceStatusServiceImpl implements InvoiceStatusService {
	@Autowired
	private InvoiceStatusDao invoiceStatusDao;

	@Override
	public List<InvoiceStatus> getAll() throws Exception {
		return this.invoiceStatusDao.getAll();
		
	}

	@Override
	public InvoiceStatus get(Integer feedbackStatusId) throws Exception {
		return this.invoiceStatusDao.get(feedbackStatusId);
		
	}

	@Override
	public InvoiceStatus getByCode(String code) throws Exception {
		return this.invoiceStatusDao.getByCode(code);
		
	}

	@Override
	public InvoiceStatus save(InvoiceStatus invoiceStatus) throws Exception {
		return this.invoiceStatusDao.save(invoiceStatus);
		
	}

	@Override
	public void delete(InvoiceStatus invoiceStatus) throws Exception {
		this.invoiceStatusDao.delete(invoiceStatus);
		
	}

}
