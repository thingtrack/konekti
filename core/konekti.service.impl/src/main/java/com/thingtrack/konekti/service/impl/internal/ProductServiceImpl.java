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
package com.thingtrack.konekti.service.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.domain.Product;
import com.thingtrack.konekti.domain.Warehouse;
import com.thingtrack.konekti.dao.api.ProductDao;
import com.thingtrack.konekti.service.api.ProductService;

/**
 * @author Thingtrack S.L.
 *
 */
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;

	@Override
	public List<Product> getAll() throws Exception {
		return this.productDao.getAll();
		
	}

	@Override
	public Product get(Integer productId) throws Exception {
		return this.productDao.get(productId);
		
	}

	@Override
	public Product getByCode(String code) throws Exception {
		return this.productDao.getByCode(code);
		
	}

	@Override
	public Product save(Product product) throws Exception {
		return this.productDao.save(product);
		
	}

	@Override
	public void delete(Product product) throws Exception {
		this.productDao.delete(product);
		
	}

	@Override
	public List<Product> getAllProductByWarehouse(Warehouse warehouse)
			throws Exception {
		return this.productDao.getAllProductByWarehouse(warehouse);
	}

}
