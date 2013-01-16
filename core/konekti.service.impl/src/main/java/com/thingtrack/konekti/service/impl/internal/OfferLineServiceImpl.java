package com.thingtrack.konekti.service.impl.internal;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.api.OfferLineDao;
import com.thingtrack.konekti.dao.api.OfferLineStatusDao;
import com.thingtrack.konekti.domain.Offer;
import com.thingtrack.konekti.domain.OfferLine;
import com.thingtrack.konekti.service.api.OfferLineService;

public class OfferLineServiceImpl implements OfferLineService {
	@Autowired
	private OfferLineDao offerLineDao;

	@Autowired
	private OfferLineStatusDao offerLineStatusDao;
	
	@Override
	public List<OfferLine> getAll() throws Exception {
		return this.offerLineDao.getAll();
		
	}

	@Override
	public OfferLine get(Integer offerLineId) throws Exception {
		return this.offerLineDao.get(offerLineId);
		
	}

	@Override
	public OfferLine getByNumber(Integer number) throws Exception {
		return this.offerLineDao.getByNumber(number);
	}

	@Override
	public OfferLine save(OfferLine offerLine) throws Exception {
		return this.offerLineDao.save(offerLine);
		
	}

	@Override
	public void delete(OfferLine offerLine) throws Exception {
		this.offerLineDao.delete(offerLine);
		
	}

	@Override
	public OfferLine createNewOfferLine(Offer offer) throws Exception {
		OfferLine offerLine = new OfferLine();
				
		offerLine.setOffer(offer);
		offerLine.setNumber(offer.getOfferLines().size() + 1);
		offerLine.setOfferLineDate(new Date());
		offerLine.setOfferLineStatus(offerLineStatusDao.getByCode(OfferLine.STATUS.OPENED.name()));
		
		return offerLine;
	}
}
