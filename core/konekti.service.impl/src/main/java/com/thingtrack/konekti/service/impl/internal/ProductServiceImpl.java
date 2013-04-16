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
import com.thingtrack.konekti.domain.Sequence;
import com.thingtrack.konekti.domain.Area;
import com.thingtrack.konekti.domain.User;
import com.thingtrack.konekti.dao.api.ProductDao;
import com.thingtrack.konekti.service.api.ProductService;
import com.thingtrack.konekti.service.api.SequenceService;

/**
 * @author Thingtrack S.L.
 *
 */
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private SequenceService sequenceService;
	
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
	public Product getByCode(String code, String version) throws Exception {
		return this.productDao.getByCode(code, version);
		
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
	public List<Product> getAll(User user) throws Exception {
		return this.productDao.getAll(user);
	}
	
	@Override
	public Product createNewEntity(Area area) throws Exception {
		Product product = new Product();
		
		product.setCode(sequenceService.setNextSequence(Sequence.CODE.PRODUCT.name()));
		product.getAreas().add(area);
		product.setProductActive(true);
		
		return product;
	}

}
