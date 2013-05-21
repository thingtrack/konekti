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
package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.Product;
import com.thingtrack.konekti.domain.Area;
import com.thingtrack.konekti.domain.User;

/**
 * @author Thingtrack S.L.
 *
 */
public interface ProductService {
	public List<Product> getAll() throws Exception;
	public Product get( Integer productId ) throws Exception;
	public Product getByCode( String code ) throws Exception;
	public Product getByCode( String code, String version ) throws Exception;
	public Product save(Product product) throws Exception;
	public void delete(Product product) throws Exception;
	public List<Product> getAll(User user) throws Exception;
	public List<Product> getAll(User user, boolean active) throws Exception;
	public Product createNewEntity(Area area) throws Exception;
}
