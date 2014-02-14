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

import java.util.List;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.Alarm;
import com.thingtrack.konekti.domain.User;

/**
 * Alarm Data Access Object 
 * <p>
 * @author Thingtrack S.L
 *
 */
public interface AlarmDao extends Dao<Alarm, Integer> {
	
	/**
	 * Obtains an {@link Alarm} object by the message it produces
	 * 
	 * @param message  the message the {@code Alarm} produces when it is triggered, not null
	 * @return an {@code Alarm} object, not null
	 * @throws Exception if there is no {@code Alarm} associated to the give {@code message}
	 */
	public Alarm getByMessage(String message) throws Exception;
	
	/**
	 * Obtains all {@link Alarm} associated to the given {@link User}
	 * 
	 * @param user  the {@link User} to search in, not null
	 * @return the {@code List<Alarm>} of alarms found
	 * @throws Exception if there is no {@link Alarm} found
	 */
	public List<Alarm> getAll(User user) throws Exception;

}
