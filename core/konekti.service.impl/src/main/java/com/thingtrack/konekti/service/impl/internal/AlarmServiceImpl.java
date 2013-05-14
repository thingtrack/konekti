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

import com.thingtrack.konekti.domain.Alarm;
import com.thingtrack.konekti.domain.User;
import com.thingtrack.konekti.dao.api.AlarmDao;
import com.thingtrack.konekti.service.api.AlarmService;

/**
 * @author Thingtrack S.L.
 *
 */
public class AlarmServiceImpl implements AlarmService {
	@Autowired
	private AlarmDao alarmDao;

	@Override
	public List<Alarm> getAll() throws Exception {
		return this.alarmDao.getAll();
		
	}

	@Override
	public Alarm get(Integer alarmId) throws Exception {
		return this.alarmDao.get(alarmId);
		
	}

	@Override
	public Alarm getByMessage(String message) throws Exception {
		return this.alarmDao.getByMessage(message);
		
	}

	@Override
	public Alarm save(Alarm alarm) throws Exception {
		return this.alarmDao.save(alarm);
		
	}

	@Override
	public void delete(Alarm alarm) throws Exception {
		this.alarmDao.delete(alarm);
		
	}

	@Override
	public List<Alarm> getAll(User user) throws Exception {
		return this.alarmDao.getAll(user);
		
	}
}
