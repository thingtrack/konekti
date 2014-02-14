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
package com.thingtrack.konekti.dao.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.OrganizationType;

/**
 * Organization Type Data Access Layer
 * <p>
 * @author Thingtrack S.L.
 *
 */
public interface OrganizationTypeDao extends Dao<OrganizationType, Integer> {
	
	/**
	 * Obtains {@link OrganizationType} by its code
	 * @param code the unique code, not null
	 * @return the queryied organization
	 * @throws Exception if the supplied code is null
	 */
	public OrganizationType getByCode(String code) throws Exception;
	
}
