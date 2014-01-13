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
package com.thingtrack.konekti.domain;

/*
 * #%L
 * Konekti Domain Layer
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2010 - 2014 Thingtrack s.l.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.io.Serializable;

import javax.persistence.AttributeOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity class
 * <p>
 * Represents the any client agent
 * <p>
 * @author Thingtrack S.L.
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "CLIENT")
@AttributeOverrides({
	@AttributeOverride(name="agentId", column=@Column(name="CLIENT_ID"))
})
public class Client extends Agent implements Serializable {
	/**
	 * Unique code, not null
	 */
	@Column(name = "CODE", nullable = false, unique = true, length = 64)
	private String code;
	
	/**
	 * Descripcion
	 */
	@Column(name = "DESCRIPTION", length = 256)
	private String description;
	
	/**
	 * Value Added Tax
	 */
	@Column(name = "VAT", length = 64)
	private String vat;
	
	/**
	 * {@link ClientType}, not null
	 */
	@ManyToOne
	@JoinColumn(name = "CLIENT_TYPE_ID", nullable = false)
	private ClientType clientType;

	/**
	 * {@link ClientGroup}, not null
	 */
	@ManyToOne
	@JoinColumn(name = "CLIENT_GROUP_ID", nullable = false)
	private ClientGroup clientGroup;
	
	/**
	 * {@link Organization} owner, not null
	 */
	@ManyToOne
	@JoinColumn(name = "ORGANIZATION_ID", nullable = false)
	private Organization organization;
	
	/**
	 * Active, not null
	 */
	@Column(name = "ACTIVE", nullable = false)
	private Boolean active = true;
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the vat
	 */
	public String getVat() {
		return vat;
	}

	/**
	 * @param vat the vat to set
	 */
	public void setVat(String vat) {
		this.vat = vat;
	}
	
	/**
	 * @param clientType
	 *            the clientType to set
	 */
	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	/**
	 * @return the clientType
	 */
	public ClientType getClientType() {
		return clientType;
	}

	/**
	 * @param clientGroup
	 *            the clientGroup to set
	 */
	public void setClientGroup(ClientGroup clientGroup) {
		this.clientGroup = clientGroup;
	}

	/**
	 * @return the clientGroup
	 */
	public ClientGroup getClientGroup() {
		return clientGroup;
	}

	/**
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * @return the organization
	 */
	public Organization getOrganization() {
		return organization;
	}

	/**
	 * @param organization the organization to set
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
