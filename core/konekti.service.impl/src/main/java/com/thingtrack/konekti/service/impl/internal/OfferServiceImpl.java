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

import com.thingtrack.konekti.domain.Offer;
import com.thingtrack.konekti.domain.OfferRequest;
import com.thingtrack.konekti.domain.OfferStatus;
import com.thingtrack.konekti.domain.OfferType;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.Sequence;
import com.thingtrack.konekti.dao.api.OfferDao;
import com.thingtrack.konekti.dao.api.OfferStatusDao;
import com.thingtrack.konekti.dao.api.OfferTypeDao;
import com.thingtrack.konekti.service.api.OfferService;
import com.thingtrack.konekti.service.api.SequenceService;

/**
 * @author Thingtrack S.L.
 *
 */
public class OfferServiceImpl implements OfferService {
	@Autowired
	private OfferDao offerDao;

	@Autowired
	private OfferStatusDao offerStatusDao;

	@Autowired
	private OfferTypeDao offerTypeDao;
	
	@Autowired
	private SequenceService sequenceService;
	
	@Override
	public List<Offer> getAll() throws Exception {
		return this.offerDao.getAll();
		
	}

	@Override
	public Offer get(Integer id) throws Exception {
		return this.offerDao.get(id);
		
	}

	@Override
	public Offer getByCode(String code) throws Exception {
		return this.offerDao.getByCode(code);		
		
	}

	@Override
	public Offer save(Offer offer) throws Exception {	
		return this.offerDao.save(offer);
			
	}
	
	@Override
	public void delete(Offer offer) throws Exception {
		this.offerDao.delete(offer);	
		
	}

	@Override
	public Offer createNewOffer(Organization organization) throws Exception {
		Offer offer = new Offer();
		
		offer.setCode(sequenceService.setNextSequence(Sequence.CODE.OFFER.name()));
		offer.setOrganization(organization);
		offer.setOfferDate(new Date());
		offer.setOfferStatus(offerStatusDao.getByCode(Offer.STATUS.OPENED.name()));
		
		return offer;
	}
	
	@Override
	public Offer createOffer(Organization organization, OfferRequest offerRequest) throws Exception {
		// get OPENED Offer status
		OfferStatus offerStatus = offerStatusDao.getByCode(Offer.STATUS.OPENED.name());
		
		// get default Offer Type
		OfferType offerType = null;
		try {
			offerType = offerTypeDao.getByCode(offerRequest.getOfferRequestType().getCode());
		}
		catch (Exception e) {
			offerType = offerTypeDao.getByCode(Offer.TYPE.OTHERS.name());
		}
		
		Offer offer = new Offer();
		offer.setOrganization(organization);
		offer.setCode(sequenceService.setNextSequence(Sequence.CODE.OFFER.name()));
		offer.setOfferClient(offerRequest.getClient());
		offer.setOfferStatus(offerStatus);
		offer.setOfferType(offerType);
		offer.setOfferDate(new Date());
		offer.setOfferRequest(offerRequest);
		
		return offer;
	}

}
