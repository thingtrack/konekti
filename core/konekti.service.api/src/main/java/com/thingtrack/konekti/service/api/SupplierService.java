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

import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.Supplier;
import com.thingtrack.konekti.domain.User;

/**
 * @author Thingtrack S.L.
 *
 */
public interface SupplierService {
	public List<Supplier> getAll(Organization organization) throws Exception;
	public Supplier get( Integer supplierId ) throws Exception;
	public Supplier getByCode(Organization organization, String code ) throws Exception;
	public Supplier save(Supplier supplier) throws Exception;
	public void delete(Supplier supplier) throws Exception;
	public Supplier createNewSupplier(Organization organization) throws Exception;
	public Supplier getByUser(User user) throws Exception;
	public List<Supplier> getAll(User user) throws Exception;
	public List<Supplier> getAll(User user, boolean active) throws Exception;
}
