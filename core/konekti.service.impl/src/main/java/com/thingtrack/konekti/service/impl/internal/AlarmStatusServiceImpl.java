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

import com.thingtrack.konekti.domain.AlarmStatus;
import com.thingtrack.konekti.dao.api.AlarmStatusDao;
import com.thingtrack.konekti.service.api.AlarmStatusService;

/**
 * @author Thingtrack S.L.
 *
 */
public class AlarmStatusServiceImpl implements AlarmStatusService {
	@Autowired
	private AlarmStatusDao alarmStatusDao;

	@Override
	public List<AlarmStatus> getAll() throws Exception {
		return this.alarmStatusDao.getAll();
		
	}

	@Override
	public AlarmStatus get(Integer alarmStatusId) throws Exception {
		return this.alarmStatusDao.get(alarmStatusId);
		
	}

	@Override
	public AlarmStatus getByCode(String code) throws Exception {
		return this.alarmStatusDao.getByCode(code);
		
	}

	@Override
	public AlarmStatus save(AlarmStatus alarmStatus) throws Exception {
		return this.alarmStatusDao.save(alarmStatus);
		
	}

	@Override
	public void delete(AlarmStatus alarmStatus) throws Exception {
		this.alarmStatusDao.delete(alarmStatus);
		
	}
	

}
