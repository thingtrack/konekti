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
import com.thingtrack.konekti.domain.InvoiceStatus;

/**
 * @author Thingtrack S.L.
 *
 */
public interface InvoiceStatusDao extends Dao<InvoiceStatus, Integer> {
	public InvoiceStatus getByCode( String code ) throws Exception;

}
