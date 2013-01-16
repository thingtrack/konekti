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

import com.thingtrack.konekti.domain.Stock;
import com.thingtrack.konekti.domain.Warehouse;

/**
 * @author Thingtrack S.L.
 *
 */
public interface StockService {
	public List<Stock> getAll() throws Exception;
	public Stock get( Integer stockId ) throws Exception;
	public Stock save(Stock stock) throws Exception;
	public void delete(Stock stock) throws Exception;
	public Stock createNewStock(Warehouse warehouse) throws Exception;
}
