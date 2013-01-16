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

import com.thingtrack.konekti.domain.OfferRequest;
import com.thingtrack.konekti.domain.Organization;

/**
 * @author Thingtrack S.L.
 *
 */
public interface OfferRequestService {
	public List<OfferRequest> getAll() throws Exception;
	public OfferRequest get( Integer offerRequestId ) throws Exception;
	public OfferRequest getByCode( String code ) throws Exception;
	public OfferRequest save(OfferRequest offerRequest) throws Exception;
	public OfferRequest createNewOfferRequest(Organization organization) throws Exception;
	public void delete(OfferRequest offerRequest) throws Exception;
	public void setOpenedStatus(OfferRequest offerRequest) throws Exception;
	public void setPendingStatus(OfferRequest offerRequest) throws Exception;
	public void setTransferredStatus(OfferRequest offerRequest) throws Exception;
	public void setRejectStatus(OfferRequest offerRequest) throws Exception;
	public void setCloseStatus(OfferRequest offerRequest) throws Exception;
}
