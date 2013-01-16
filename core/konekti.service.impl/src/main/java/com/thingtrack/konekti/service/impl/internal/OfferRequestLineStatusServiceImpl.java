package com.thingtrack.konekti.service.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.api.OfferRequestLineStatusDao;
import com.thingtrack.konekti.domain.OfferRequestLineStatus;
import com.thingtrack.konekti.service.api.OfferRequestLineStatusService;

/**
 * @author Thingtrack S.L.
 *
 */
public class OfferRequestLineStatusServiceImpl implements OfferRequestLineStatusService {
	@Autowired
	private OfferRequestLineStatusDao offerRequestLineStatusDao;

	@Override
	public List<OfferRequestLineStatus> getAll() throws Exception {
		return this.offerRequestLineStatusDao.getAll();
		
	}

	@Override
	public OfferRequestLineStatus get(Integer offerRequestLineStatusId) throws Exception {
		return this.offerRequestLineStatusDao.get(offerRequestLineStatusId);
	}

	@Override
	public OfferRequestLineStatus getByCode(String code) throws Exception {
		return this.offerRequestLineStatusDao.getByCode(code);
		
	}

	@Override
	public OfferRequestLineStatus save(OfferRequestLineStatus offerRequestLineStatus) throws Exception {
		return this.offerRequestLineStatusDao.save(offerRequestLineStatus);
		
	}

	@Override
	public void delete(OfferRequestLineStatus offerRequestLineStatus) throws Exception {
		this.offerRequestLineStatusDao.delete(offerRequestLineStatus);
		
	}


}
