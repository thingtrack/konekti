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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Thingtrack S.L.
 *
 */
@Entity
@Table(name="SERVICE_TYPE")
public class ServiceType {
	@Id
	@Column(name="SERVICE_TYPE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer serviceTypeId;
	
	@Column(name="CODE", nullable=false, unique=true, length=256)
	@Size(min=1, max=256)
	@NotNull
	private String code;
	
	@Column(name="DESCRIPTION", length=1024)
	@Size(min=1, max=1024)
	private String description;

	public enum TYPE {
		PLANNED(1),
		UNPLANNED(2);
		
	   /**
	     * Value for this TYPE
	    */		
	    private final Integer Value;
	 
	    private TYPE(Integer value) {
	        Value = value;
	        
	    }
	    
		// Mapping TYPE to Integer id
	    private static final Map<Integer, TYPE> _map = new HashMap<Integer, TYPE>();
	    
	    static {
	        for (TYPE type : TYPE.values())
	            _map.put(type.Value, type);
	        
	    }
	    
	    /**
	     * Get TYPE from value
	     * @param value Value
	     * @return TYPE
	     */
	    public static TYPE from(Integer value) {
	        return _map.get(value);
	        
	    }
	    
	    /**
	     * Get Integer from value
	     * @param TYPE Value
	     * @return Integer
	     */
	    @SuppressWarnings("rawtypes")
		public static Integer from(TYPE value) {
	    	Iterator it = _map.entrySet().iterator();
	    	
	    	while (it.hasNext()) {
	    		Map.Entry e = (Map.Entry)it.next();
	    		
	    		if (e.getValue() == value)
	    			return (Integer) e.getKey();
	    	}
	    	
	        return null;
	        
	    }
	}
	
	/**
	 * @param serviceTypeId the serviceTypeId to set
	 */
	public void setServiceTypeId(Integer serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}

	/**
	 * @return the serviceTypeId
	 */
	public Integer getServiceTypeId() {
		return serviceTypeId;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ServiceType))
			return false;
		ServiceType other = (ServiceType) obj;
		if (serviceTypeId == null) {
			if (other.serviceTypeId != null)
				return false;
		} else if (!serviceTypeId.equals(other.serviceTypeId))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
}
