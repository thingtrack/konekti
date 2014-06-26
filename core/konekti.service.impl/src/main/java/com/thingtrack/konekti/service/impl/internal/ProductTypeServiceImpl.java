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

import com.thingtrack.konekti.domain.Area;
import com.thingtrack.konekti.domain.ProductType;
import com.thingtrack.konekti.dao.api.ProductTypeDao;
import com.thingtrack.konekti.service.api.ProductTypeService;

/**
 * @author Thingtrack S.L.
 *
 */
public class ProductTypeServiceImpl implements ProductTypeService {
	@Autowired
	private ProductTypeDao productTypeDao;

	@Override
	public List<ProductType> getAll(Area area) throws Exception {
		return this.productTypeDao.getAll(area);
		
	}

	@Override
	public ProductType get(Integer productId) throws Exception {
		return this.productTypeDao.get(productId);
		
	}

	@Override
	public ProductType getByCode(Area area, String code) throws Exception {
		return this.productTypeDao.getByCode(area, code);
		
	}

	@Override
	public ProductType getByDescription(String description) throws Exception {
		return this.productTypeDao.getByDescription(description);
	}
	
	@Override
	public ProductType save(ProductType productType) throws Exception {
		return this.productTypeDao.save(productType);
		
	}

	@Override
	public void delete(ProductType productType) throws Exception {
		this.productTypeDao.delete(productType);
		
	}
	
}
