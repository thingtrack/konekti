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

import com.thingtrack.konekti.domain.AlarmType;
import com.thingtrack.konekti.dao.api.AlarmTypeDao;
import com.thingtrack.konekti.service.api.AlarmTypeService;

/**
 * @author Thingtrack S.L.
 *
 */
public class AlarmTypeServiceImpl implements AlarmTypeService {
	@Autowired
	private AlarmTypeDao alarmTypeDao;

	@Override
	public List<AlarmType> getAll() throws Exception {
		return this.alarmTypeDao.getAll();
		
	}

	@Override
	public AlarmType get(Integer alarmTypeId) throws Exception {
		return this.alarmTypeDao.get(alarmTypeId);
		
	}

	@Override
	public AlarmType getByCode(String code) throws Exception {
		return this.alarmTypeDao.getByCode(code);
		
	}

	@Override
	public AlarmType save(AlarmType alarmType) throws Exception {
		return this.alarmTypeDao.save(alarmType);
		
	}

	@Override
	public void delete(AlarmType alarmType) throws Exception {
		this.alarmTypeDao.delete(alarmType);
		
	}

}
