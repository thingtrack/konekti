package com.thingtrack.konekti.dao.impl.internal;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.dao.api.WarehouseTypeDao;
import com.thingtrack.konekti.domain.WarehouseType;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class WarehouseTypeDaoImpl extends JpaDao<WarehouseType, Integer> implements WarehouseTypeDao {
	@Override
	public WarehouseType getByName(String name) throws Exception {
		WarehouseType warehouseType = (WarehouseType)getEntityManager()
		.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.name = :name")
		.setParameter("name", name).getSingleResult();
		
		return warehouseType;

	}

}
