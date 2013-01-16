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

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.domain.Stock;
import com.thingtrack.konekti.domain.Warehouse;
import com.thingtrack.konekti.dao.api.StockDao;
import com.thingtrack.konekti.dao.api.StockStatusDao;
import com.thingtrack.konekti.service.api.StockService;

/**
 * @author Thingtrack S.L.
 *
 */
public class StockServiceImpl implements StockService {
	@Autowired
	private StockDao stockDao;

	@Autowired
	private StockStatusDao stockStatusDao;
	
	@Override
	public List<Stock> getAll() throws Exception {
		return this.stockDao.getAll();
		
	}

	@Override
	public Stock get(Integer stockId) throws Exception {
		return this.stockDao.get(stockId);
		
	}

	@Override
	public Stock save(Stock stock) throws Exception {
		return this.stockDao.save(stock);
	}

	@Override
	public void delete(Stock stock) throws Exception {
		this.stockDao.delete(stock);
		
	}

	@Override
	public Stock createNewStock(Warehouse warehouse) throws Exception {
		Stock stock = new Stock();
		
		stock.setWarehouse(warehouse);
		stock.setStockDate(new Date());
		stock.setStockStatus(stockStatusDao.getByCode(Stock.STATUS.WAREHOUSED.name()));
		
		return stock;
	}
}
