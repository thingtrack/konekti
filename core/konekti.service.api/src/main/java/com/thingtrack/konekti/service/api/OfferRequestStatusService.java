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
package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.OfferRequestStatus;

/**
 * @author Thingtrack S.L.
 *
 */
public interface OfferRequestStatusService {
	public List<OfferRequestStatus> getAll() throws Exception;
	public OfferRequestStatus get( Integer offerRequestStatusId ) throws Exception;
	public OfferRequestStatus getByCode( String code ) throws Exception;
	public OfferRequestStatus save(OfferRequestStatus offerRequestStatus) throws Exception;
	public void delete(OfferRequestStatus offerRequestStatus) throws Exception;
}
