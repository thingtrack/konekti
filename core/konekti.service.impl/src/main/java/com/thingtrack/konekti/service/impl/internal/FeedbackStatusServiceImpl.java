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

import com.thingtrack.konekti.domain.FeedbackStatus;
import com.thingtrack.konekti.dao.api.FeedbackStatusDao;
import com.thingtrack.konekti.service.api.FeedbackStatusService;

/**
 * @author Thingtrack S.L.
 *
 */
public class FeedbackStatusServiceImpl implements FeedbackStatusService  {
	@Autowired
	private FeedbackStatusDao feedbackStatusDao;

	@Override
	public List<FeedbackStatus> getAll() throws Exception {
		return this.feedbackStatusDao.getAll();
		
	}

	@Override
	public FeedbackStatus get(Integer feedbackStatusId) throws Exception {
		return this.feedbackStatusDao.get(feedbackStatusId);
		
	}

	@Override
	public FeedbackStatus getByCode(String code) throws Exception {
		return this.feedbackStatusDao.getByCode(code);
		
	}

	@Override
	public FeedbackStatus save(FeedbackStatus feedbackStatus) throws Exception {
		return this.feedbackStatusDao.save(feedbackStatus);
		
	}

	@Override
	public void delete(FeedbackStatus feedbackStatus) throws Exception {
		this.feedbackStatusDao.delete(feedbackStatus);
		
	}
	
}
