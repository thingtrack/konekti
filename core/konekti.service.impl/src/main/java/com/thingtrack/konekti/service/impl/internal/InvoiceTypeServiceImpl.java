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

import com.thingtrack.konekti.domain.InvoiceType;
import com.thingtrack.konekti.dao.api.InvoiceTypeDao;
import com.thingtrack.konekti.service.api.InvoiceTypeService;

/**
 * @author Thingtrack S.L.
 *
 */
public class InvoiceTypeServiceImpl implements InvoiceTypeService {
	@Autowired
	private InvoiceTypeDao invoiceTypeDao;

	@Override
	public List<InvoiceType> getAll() throws Exception {
		return this.invoiceTypeDao.getAll();
		
	}

	@Override
	public InvoiceType get(Integer feedbackStatusId) throws Exception {
		return this.invoiceTypeDao.get(feedbackStatusId);
		
	}

	@Override
	public InvoiceType getByCode(String code) throws Exception {
		return this.invoiceTypeDao.getByCode(code);
		
	}

	@Override
	public InvoiceType save(InvoiceType invoiceType) throws Exception {
		return this.invoiceTypeDao.save(invoiceType);
		
	}

	@Override
	public void delete(InvoiceType invoiceType) throws Exception {
		this.invoiceTypeDao.delete(invoiceType);
		
	}

}
