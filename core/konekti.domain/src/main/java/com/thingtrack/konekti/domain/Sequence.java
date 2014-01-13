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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="SEQUENCE")
public class Sequence implements Serializable {
	@Id
	@Column(name="SEQUENCE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer sequenceId;

	@Column(name="CODE", nullable=false, unique=true, length=30)
	@NotNull
	private String code;
	
	@Column(name="NAME", nullable=false, unique=true, length=30)
	@NotNull
	private String name;
	
	@Column(name="VALUE", nullable=false, length=10)
	@NotNull
	private int value;
	
	@Column(name="DESCRIPTION", nullable=false, unique=true, length=255)
	@NotNull
	private String description;
	
	public enum CODE {        
		CLIENT,
		INVOICE,
		OFFER,
		OFFER_REQUEST,
		ROUTE,
		SERVICE,
		ORGANIZATION,
		LOCATION,
		AREA,
		WORKSHOP,
		GAS_STATION,
		SUPPLIER,
		REPORT,
		PRODUCT,
		ROLE,
		PERMISSION,
		ACTION
    }
	
	/**
	 * @return the sequenceId
	 */
	public Integer getSequenceId() {
		return sequenceId;
	}

	/**
	 * @param sequenceId the sequenceId to set
	 */
	public void setSequenceId(Integer sequenceId) {
		this.sequenceId = sequenceId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
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
}
