package com.thingtrack.konekti.dao.impl.internal;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.dao.api.OfferLineStatusDao;
import com.thingtrack.konekti.domain.OfferLineStatus;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class OfferLineStatusDaoImpl extends JpaDao<OfferLineStatus, Integer> implements OfferLineStatusDao {
	@Override
	public OfferLineStatus getByCode(String code) throws Exception {
		OfferLineStatus offerLineStatus = (OfferLineStatus)getEntityManager()
		.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.code = :code")
		.setParameter("code", code).getSingleResult();

		return offerLineStatus;
		
	}

}
