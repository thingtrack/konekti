package com.thingtrack.konekti.dao.impl.internal;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.dao.api.OfferRequestLineStatusDao;
import com.thingtrack.konekti.domain.OfferRequestLineStatus;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class OfferRequestLineStatusDaoImpl extends JpaDao<OfferRequestLineStatus, Integer> implements OfferRequestLineStatusDao {
	@Override
	public OfferRequestLineStatus getByCode(String code) throws Exception {
		OfferRequestLineStatus offerRequestLineStatus = (OfferRequestLineStatus)getEntityManager()
		.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.code = :code")
		.setParameter("code", code).getSingleResult();

		return offerRequestLineStatus;

	}
}
