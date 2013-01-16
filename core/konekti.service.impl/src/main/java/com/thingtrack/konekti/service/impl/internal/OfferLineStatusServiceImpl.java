package com.thingtrack.konekti.service.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.api.OfferLineStatusDao;
import com.thingtrack.konekti.domain.OfferLineStatus;
import com.thingtrack.konekti.service.api.OfferLineStatusService;

public class OfferLineStatusServiceImpl implements OfferLineStatusService {
	@Autowired
	private OfferLineStatusDao offerLineStatusDao;

	@Override
	public List<OfferLineStatus> getAll() throws Exception {
		return this.offerLineStatusDao.getAll();
		
	}

	@Override
	public OfferLineStatus get(Integer offerLineId) throws Exception {
		return this.offerLineStatusDao.get(offerLineId);
		
	}

	@Override
	public OfferLineStatus getByCode(String code) throws Exception {
		return this.offerLineStatusDao.getByCode(code);
		
	}

	@Override
	public OfferLineStatus save(OfferLineStatus offerLineStatus) throws Exception {
		return this.offerLineStatusDao.save(offerLineStatus);
	}

	@Override
	public void delete(OfferLineStatus offerLineStatus) throws Exception {
		this.offerLineStatusDao.delete(offerLineStatus);
		
	}

}
