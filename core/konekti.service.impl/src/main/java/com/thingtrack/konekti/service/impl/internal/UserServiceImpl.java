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

import com.thingtrack.konekti.domain.User;
import com.thingtrack.konekti.dao.api.UserDao;
import com.thingtrack.konekti.service.api.UserService;

/**
 * @author Thingtrack S.L.
 *
 */
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public List<User> getAll() throws Exception {
		return this.userDao.getAll();
		
	}

	@Override
	public User get(Integer userId) throws Exception {
		return this.userDao.get(userId);
		
	}

	@Override
	public User getByUsername(String username) throws Exception {
		return this.userDao.getByUsername(username);
		
	}

	@Override
	public User save(User user) throws Exception {
		return this.userDao.save(user);
	}

	@Override
	public void delete(User user) throws Exception {
		this.userDao.delete(user);
		
	}

}
