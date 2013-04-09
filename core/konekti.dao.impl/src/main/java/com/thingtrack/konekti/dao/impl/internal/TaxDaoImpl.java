package com.thingtrack.konekti.dao.impl.internal;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.dao.api.TaxDao;
import com.thingtrack.konekti.domain.Tax;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class TaxDaoImpl extends JpaDao<Tax, Integer> implements TaxDao {
	@Override
	public Tax getByCode(String code) throws Exception {
		Tax tax = (Tax)getEntityManager()
		.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.code = :code")
		.setParameter("code", code).getSingleResult();

		return tax;

	}
	
}
