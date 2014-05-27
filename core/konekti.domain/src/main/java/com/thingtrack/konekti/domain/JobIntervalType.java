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

/**
 * Entity class
 * <p>
 * Represents the job interval type
 * <p>
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="JOB_INTERVAL_TYPE")
public class JobIntervalType implements Serializable {
	/**
	 * Unique identifier
	 */
	@Id
	@Column(name="JOB_INTERVAL_TYPE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer jobIntervalTypeId;
	
	/**
	 * Code
	 */
	@Column(name="CODE")
	private String code;
	
	/**
	 * Description
	 */
	@Column(name="DESCRIPTION")
	private String description;

	public Integer getJobIntervalTypeId() {
		return jobIntervalTypeId;
	}

	public void setJobIntervalTypeId(Integer jobIntervalTypeId) {
		this.jobIntervalTypeId = jobIntervalTypeId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "JobIntervalType [jobIntervalTypeId=" + jobIntervalTypeId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JobIntervalType other = (JobIntervalType) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	
	
}
