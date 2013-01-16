package com.thingtrack.konekti.dao.impl.internal;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.dao.api.IvaDao;
import com.thingtrack.konekti.domain.Iva;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class IvaDaoImpl extends JpaDao<Iva, Integer> implements IvaDao {
	@Override
	public Iva getByCode(String code) throws Exception {
		Iva iva = (Iva)getEntityManager()
		.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.code = :code")
		.setParameter("code", code).getSingleResult();

		return iva;

	}
	
}
