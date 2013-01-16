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

import com.thingtrack.konekti.domain.StockStatus;
import com.thingtrack.konekti.dao.api.StockStatusDao;
import com.thingtrack.konekti.service.api.StockStatusService;

/**
 * @author Thingtrack S.L.
 *
 */
public class StockStatusServiceImpl implements StockStatusService {
	@Autowired
	private StockStatusDao stockStatusDao;

	@Override
	public List<StockStatus> getAll() throws Exception {
		return this.stockStatusDao.getAll();
		
	}

	@Override
	public StockStatus get(Integer stockStatusId) throws Exception {
		return this.stockStatusDao.get(stockStatusId);
		
	}

	@Override
	public StockStatus getByCode(String code) throws Exception {
		return this.stockStatusDao.getByCode(code);
		
	}

	@Override
	public StockStatus save(StockStatus stockStatus) throws Exception {
		return this.stockStatusDao.save(stockStatus);
		
	}

	@Override
	public void delete(StockStatus stockStatus) throws Exception {
		this.stockStatusDao.delete(stockStatus);
		
	}

}
