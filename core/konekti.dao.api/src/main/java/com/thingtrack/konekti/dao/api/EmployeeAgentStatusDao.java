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
import com.thingtrack.konekti.domain.EmployeeAgentStatus;

/**
 * Employee Agent Status Data Accesso Layer
 * <p>
 * @author Thingtrack S.L.
 *
 */
public interface EmployeeAgentStatusDao extends Dao<EmployeeAgentStatus, Integer> {
	
	/**
	 * Obtains the {@link EmployeeAgentStatus} associated to the {@code name}
	 *  
	 * @param name  the name of the status, not null
  	 * @return The EmployeeAgentStatus which corresponds the given name
	 * @throws Exception if the EmployeeAgentStatus associated to that name or it is null
	 */
	public EmployeeAgentStatus getByName(String name) throws Exception;
	
}
