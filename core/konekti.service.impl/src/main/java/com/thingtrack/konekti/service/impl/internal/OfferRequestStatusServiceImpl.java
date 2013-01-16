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

import com.thingtrack.konekti.domain.OfferRequestStatus;
import com.thingtrack.konekti.dao.api.OfferRequestStatusDao;
import com.thingtrack.konekti.service.api.OfferRequestStatusService;

/**
 * @author Thingtrack S.L.
 *
 */
public class OfferRequestStatusServiceImpl implements OfferRequestStatusService {
	@Autowired
	private OfferRequestStatusDao offerRequestStatusDao;

	@Override
	public List<OfferRequestStatus> getAll() throws Exception {
		return this.offerRequestStatusDao.getAll();
		
	}

	@Override
	public OfferRequestStatus get(Integer offerRequestStatusId) throws Exception {
		return this.offerRequestStatusDao.get(offerRequestStatusId);
		
	}

	@Override
	public OfferRequestStatus getByCode(String code) throws Exception {
		return this.offerRequestStatusDao.getByCode(code);
		
	}

	@Override
	public OfferRequestStatus save(OfferRequestStatus offerRequestStatus) throws Exception {
		return this.offerRequestStatusDao.save(offerRequestStatus);
		
	}

	@Override
	public void delete(OfferRequestStatus offerRequestStatus) throws Exception {
		this.offerRequestStatusDao.delete(offerRequestStatus);
		
	}

}
