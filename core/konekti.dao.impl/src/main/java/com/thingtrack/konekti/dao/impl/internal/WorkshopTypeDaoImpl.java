package com.thingtrack.konekti.dao.impl.internal;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.dao.api.WorkshopTypeDao;
import com.thingtrack.konekti.domain.WorkshopType;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class WorkshopTypeDaoImpl extends JpaDao<WorkshopType, Integer> implements WorkshopTypeDao {
	@Override
	public WorkshopType getByCode(String code) throws Exception {
		WorkshopType workshopType = (WorkshopType)getEntityManager()
		.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.code = :code")
		.setParameter("code", code).getSingleResult();

		return workshopType;
		
	}

}
