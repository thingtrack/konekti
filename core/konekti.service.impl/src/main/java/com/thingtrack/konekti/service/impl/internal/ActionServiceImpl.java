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

import com.thingtrack.konekti.domain.Action;
import com.thingtrack.konekti.dao.api.ActionDao;
import com.thingtrack.konekti.service.api.ActionService;

/**
 * @author Thingtrack S.L.
 *
 */
public class ActionServiceImpl implements ActionService {
	@Autowired
	private ActionDao actionDao;

	@Override
	public List<Action> getAll() throws Exception {
		return this.actionDao.getAll();
		
	}

	@Override
	public Action get(Integer actionId) throws Exception {
		return this.actionDao.get(actionId);
				
	}

	@Override
	public Action getByCode(String code) throws Exception {
		return this.actionDao.getByCode(code);
		
	}

	@Override
	public Action save(Action action) throws Exception {
		return this.actionDao.save(action);
		
	}

	@Override
	public void delete(Action action) throws Exception {
		this.actionDao.delete(action);
		
	}
	
}
