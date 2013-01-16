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

import com.thingtrack.konekti.domain.Feedback;
import com.thingtrack.konekti.dao.api.FeedbackDao;
import com.thingtrack.konekti.service.api.FeedbackService;

/**
 * @author Thingtrack S.L.
 *
 */
public class FeedbackServiceImpl implements FeedbackService {
	@Autowired
	private FeedbackDao feedbackDao;

	@Override
	public List<Feedback> getAll() throws Exception {
		return this.feedbackDao.getAll();
		
	}

	@Override
	public Feedback get(Integer clientGroupId) throws Exception {
		return this.feedbackDao.get(clientGroupId);
		
	}

	@Override
	public Feedback save(Feedback feedback) throws Exception {
		return this.feedbackDao.save(feedback);
		
	}

	@Override
	public void delete(Feedback feedback) throws Exception {
		this.feedbackDao.delete(feedback);
		
	}

}
