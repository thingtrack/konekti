package com.thingtrack.konekti.dao.impl.internal;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.dao.api.OfferRequestLineDao;
import com.thingtrack.konekti.domain.OfferRequestLine;
import com.thingtrack.konekti.domain.OfferRequestLineStatus;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class OfferRequestLineDaoImpl extends JpaDao<OfferRequestLine, Integer> implements OfferRequestLineDao {
	@Override
	public OfferRequestLine getByNumber(Integer number) throws Exception {
		OfferRequestLine offerRequestLine = (OfferRequestLine)getEntityManager()
		.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.number = :number")
		.setParameter("number", number).getSingleResult();

		return offerRequestLine;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OfferRequestLine> getByStatus(OfferRequestLineStatus offerRequestLineStatus) throws Exception {
		return (List<OfferRequestLine>) getEntityManager()
		.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.offerRequestLineStatus = :offerRequestLineStatus")
		.setParameter("offerRequestLineStatus", offerRequestLineStatus).getResultList();
	}
	
}
