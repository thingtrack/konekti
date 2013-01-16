package com.thingtrack.konekti.dao.impl.internal;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.dao.api.WorkshopDao;
import com.thingtrack.konekti.domain.Workshop;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class WorkshopDaoImpl extends JpaDao<Workshop, Integer> implements WorkshopDao {
	@Override
	public Workshop getByCode(String code) throws Exception {
		Workshop workshop = (Workshop)getEntityManager()
		.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.code = :code")
		.setParameter("code", code).getSingleResult();

		return workshop;
		
	}

}
