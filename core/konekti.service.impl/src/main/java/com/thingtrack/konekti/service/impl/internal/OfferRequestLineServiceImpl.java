package com.thingtrack.konekti.service.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.api.OfferRequestLineDao;
import com.thingtrack.konekti.domain.OfferRequestLine;
import com.thingtrack.konekti.service.api.OfferRequestLineService;

/**
 * @author Thingtrack S.L.
 *
 */
public class OfferRequestLineServiceImpl implements OfferRequestLineService {
	@Autowired
	private OfferRequestLineDao offerRequestLineDao;

	@Override
	public List<OfferRequestLine> getAll() throws Exception {
		return this.offerRequestLineDao.getAll();
		
	}

	@Override
	public OfferRequestLine get(Integer offerLineId) throws Exception {
		return this.offerRequestLineDao.get(offerLineId);
		
	}

	@Override
	public OfferRequestLine getByNumber(Integer number) throws Exception {
		return this.offerRequestLineDao.getByNumber(number);
		
	}

	@Override
	public OfferRequestLine save(OfferRequestLine offerRequestLine) throws Exception {
		return this.offerRequestLineDao.save(offerRequestLine);
	}

	@Override
	public void delete(OfferRequestLine offerRequestLine) throws Exception {
		this.offerRequestLineDao.delete(offerRequestLine);
		
	}

}
