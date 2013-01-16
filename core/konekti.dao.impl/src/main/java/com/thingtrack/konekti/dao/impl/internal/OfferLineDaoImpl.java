package com.thingtrack.konekti.dao.impl.internal;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.dao.api.OfferLineDao;
import com.thingtrack.konekti.domain.OfferLine;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class OfferLineDaoImpl extends JpaDao<OfferLine, Integer> implements OfferLineDao {
	@Override
	public OfferLine getByNumber(Integer number) throws Exception {
		OfferLine offerLine = (OfferLine)getEntityManager()
		.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.number = :number")
		.setParameter("number", number).getSingleResult();

		return offerLine;

	}

}
