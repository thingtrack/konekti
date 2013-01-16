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

import com.thingtrack.konekti.domain.OfferStatus;
import com.thingtrack.konekti.dao.api.OfferStatusDao;
import com.thingtrack.konekti.service.api.OfferStatusService;

/**
 * @author Thingtrack S.L.
 *
 */
public class OfferStatusServiceImpl implements OfferStatusService {
	@Autowired
	private OfferStatusDao offerStatusDao;

	@Override
	public List<OfferStatus> getAll() throws Exception {
		return this.offerStatusDao.getAll();
		
	}

	@Override
	public OfferStatus get(Integer offerStatusId) throws Exception {
		return this.offerStatusDao.get(offerStatusId);
		
	}

	@Override
	public OfferStatus getByCode(String code) throws Exception {
		return this.offerStatusDao.getByCode(code);
		
	}

	@Override
	public OfferStatus save(OfferStatus offerStatus) throws Exception {
		return this.offerStatusDao.save(offerStatus);
		
	}

	@Override
	public void delete(OfferStatus offerStatus) throws Exception {
		this.offerStatusDao.delete(offerStatus);
		
	}

}
