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

import com.thingtrack.konekti.domain.OfferRequest;
import com.thingtrack.konekti.domain.OfferRequestStatus;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.Sequence;
import com.thingtrack.konekti.dao.api.OfferRequestDao;
import com.thingtrack.konekti.dao.api.OfferRequestStatusDao;
import com.thingtrack.konekti.service.api.OfferRequestService;
import com.thingtrack.konekti.service.api.SequenceService;

/**
 * @author Thingtrack S.L.
 *
 */
public class OfferRequestServiceImpl implements OfferRequestService {
	@Autowired
	private OfferRequestDao offerRequestDao;

	@Autowired
	private OfferRequestStatusDao offerRequestStatusDao;
	
	@Autowired
	private SequenceService sequenceService;
	
	@Override
	public List<OfferRequest> getAll() throws Exception {
		return this.offerRequestDao.getAll();
		
	}

	@Override
	public OfferRequest get(Integer offerRequestId) throws Exception {
		return this.offerRequestDao.get(offerRequestId);
		
	}

	@Override
	public OfferRequest getByCode(String code) throws Exception {
		return this.offerRequestDao.getByCode(code);
		
	}

	@Override
	public OfferRequest save(OfferRequest offerRequest) throws Exception {
		return this.offerRequestDao.save(offerRequest);
		
	}

	@Override
	public OfferRequest createNewOfferRequest(Organization organization) throws Exception {
		OfferRequest offerRequest = new OfferRequest();
		
		offerRequest.setCode(sequenceService.setNextSequence(Sequence.CODE.OFFER_REQUEST.name()));
		offerRequest.setOrganization(organization);
		offerRequest.setOfferRequestDate(new Date());
		offerRequest.setOfferRequestStatus(offerRequestStatusDao.getByCode(OfferRequest.STATUS.OPENED.name()));
		
		return offerRequest;
	}
	
	@Override
	public void delete(OfferRequest offerRequest) throws Exception {
		this.offerRequestDao.delete(offerRequest);
		
	}

	@Override
	public void setOpenedStatus(OfferRequest offerRequest) throws Exception {
		OfferRequestStatus offerRequestStatus = offerRequestStatusDao.getByCode(OfferRequest.STATUS.OPENED.name());
		
		offerRequest.setOfferRequestStatus(offerRequestStatus);		
		offerRequestDao.save(offerRequest);		
		
	}
	
	@Override
	public void setPendingStatus(OfferRequest offerRequest) throws Exception {
		OfferRequestStatus offerRequestStatus = offerRequestStatusDao.getByCode(OfferRequest.STATUS.PENDING.name());
		
		offerRequest.setOfferRequestStatus(offerRequestStatus);		
		offerRequestDao.save(offerRequest);		
		
	}

	@Override
	public void setTransferredStatus(OfferRequest offerRequest) throws Exception {
		OfferRequestStatus offerRequestStatus = offerRequestStatusDao.getByCode(OfferRequest.STATUS.TRANSFERRED.name());
		
		offerRequest.setOfferRequestStatus(offerRequestStatus);		
		offerRequestDao.save(offerRequest);
		
	}

	@Override
	public void setRejectStatus(OfferRequest offerRequest) throws Exception {
		OfferRequestStatus offerRequestStatus = offerRequestStatusDao.getByCode(OfferRequest.STATUS.REJECTED.name());
		
		offerRequest.setOfferRequestStatus(offerRequestStatus);		
		offerRequestDao.save(offerRequest);
		
	}

	@Override
	public void setCloseStatus(OfferRequest offerRequest) throws Exception {
		OfferRequestStatus offerRequestStatus = offerRequestStatusDao.getByCode(OfferRequest.STATUS.CLOSED.name());
		
		offerRequest.setOfferRequestStatus(offerRequestStatus);		
		offerRequestDao.save(offerRequest);
		
	}
	
}
