package com.thingtrack.konekti.dao.impl.internal;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.dao.api.AreaTypeDao;
import com.thingtrack.konekti.domain.AreaType;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class AreaTypeDaoImpl extends JpaDao<AreaType, Integer> implements AreaTypeDao {
	@Override
	public AreaType getByName(String name) throws Exception {
		AreaType areaType = (AreaType)getEntityManager()
		.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.name = :name")
		.setParameter("name", name).getSingleResult();
		
		return areaType;

	}

}
