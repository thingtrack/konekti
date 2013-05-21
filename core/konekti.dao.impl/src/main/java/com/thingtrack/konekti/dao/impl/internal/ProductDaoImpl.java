/*
 * Copyright 2011 Thingtrack, S.L.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.thingtrack.konekti.dao.impl.internal;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.dao.api.ProductDao;
import com.thingtrack.konekti.domain.Product;
import com.thingtrack.konekti.domain.User;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class ProductDaoImpl extends JpaDao<Product, Integer> implements ProductDao {
	@Override
	public Product getByCode(String code) throws Exception {
		Product product = (Product)getEntityManager()
				.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.code = :code")
				.setParameter("code", code).getSingleResult();

		return product;
		
	}

	@Override
	public Product getByCode(String code, String version) throws Exception {
		Product product = (Product)getEntityManager()
				.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.code = :code AND p.version = :version")
				.setParameter("code", code)
				.setParameter("version", version).getSingleResult();

		return product;
		
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Product> getAll(User user) throws Exception {
		StringBuffer queryString = new StringBuffer("SELECT p FROM " + getEntityName() + " p");
		
		if (user.getActiveArea() != null)
			queryString.append( " WHERE :area MEMBER OF p.areas");
			
		Query query = (Query) getEntityManager().createQuery(queryString.toString());
		
		if (user.getActiveArea() != null)
			query.setParameter("area", user.getActiveArea());
		
		return query.getResultList();
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAll(User user, boolean active) throws Exception {
		StringBuffer queryString = new StringBuffer("SELECT p FROM " + getEntityName() + " p");
		
		queryString.append( " WHERE p.productActive = :active");
		
		if (user.getActiveArea() != null)
			queryString.append( " AND :area MEMBER OF p.areas");		
							
		Query query = (Query) getEntityManager().createQuery(queryString.toString());
		
		query.setParameter("active", active);
		
		if (user.getActiveArea() != null)
			query.setParameter("area", user.getActiveArea());
					
		// trigger query to refresh the cache.
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		
		return query.getResultList();
	}
}
