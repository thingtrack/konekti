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

import com.thingtrack.konekti.domain.FeedbackType;
import com.thingtrack.konekti.dao.api.FeedbackTypeDao;
import com.thingtrack.konekti.service.api.FeedbackTypeService;

/**
 * @author Thingtrack S.L.
 *
 */
public class FeedbackTypeServiceImpl implements FeedbackTypeService {
	@Autowired
	private FeedbackTypeDao feedbackTypeDao;

	@Override
	public List<FeedbackType> getAll() throws Exception {
		return this.feedbackTypeDao.getAll();
		
	}

	@Override
	public FeedbackType get(Integer feedbackStatusId) throws Exception {
		return this.feedbackTypeDao.get(feedbackStatusId);
		
	}

	@Override
	public FeedbackType getByCode(String code) throws Exception {
		return this.feedbackTypeDao.getByCode(code);
		
	}

	@Override
	public FeedbackType save(FeedbackType feedbackType) throws Exception {
		return this.feedbackTypeDao.save(feedbackType);
		
	}

	@Override
	public void delete(FeedbackType feedbackType) throws Exception {
		this.feedbackTypeDao.delete(feedbackType);
		
	}
	
}
