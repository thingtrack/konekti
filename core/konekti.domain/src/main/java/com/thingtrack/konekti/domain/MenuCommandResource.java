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

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Entity class
 * <p>
 * Represents 
 * <p>
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("COMMAND")
public class MenuCommandResource extends MenuResource {
	
	/**
	 * Hint or Tooltip
	 */
	@Column(name = "HINT")
	private String hint;
		
	/**
	 * Module identifier
	 */
	@Column(name = "MODULE_ID")
	private String moduleId;
	
	/**
	 * Module version
	 */
	@Column(name = "MODULE_VERSION")
	private String moduleVersion;
	
	/**
	 * Module type
	 * <p><ul>
	 * <li>VIEW
	 * <li>	SEPARATOR
	 * <li>REPORT
	 * <ul><p>
	 */
	@Column(name = "TYPE_MODULE")
	@Enumerated(EnumType.STRING)
	private TYPE type = TYPE.VIEW;
	
	/**
	 * Module location when it is openend
	 * <p><ul>
	 * <li>TOP
	 * <li>LEFT
	 * <li>CENTER
	 * <li>RIGHT
	 * <li>BOTTON
	 * <ul><p>
	 */
	@Column(name = "LOCATION")
	@Enumerated(EnumType.STRING)
	private LOCATION location = LOCATION.CENTER;
	
	/**
	 * Autostart
	 */
	@Column(name = "AUTOSTART")
	private boolean autostart = false;

	/**
	 * Closeable
	 */
	@Column(name = "CLOSABLE")
	private boolean closable = true;
	
	public enum LOCATION {
		TOP("Top"),
		LEFT("Left"),
		CENTER("Center"),
		RIGHT("Right"),
		BOTTON("Botton");
		
		private String name;
		private LOCATION(String name){
			this.name = name;
		}
		
		@Override
		public String toString() {
			return name;
		}
	}

	public enum TYPE {
		VIEW("View"),
		SEPARATOR("Separator"),
		REPORT("Report");
		
		private String name;
		private TYPE(String name){
			this.name = name;
		}
		
		@Override
		public String toString() {
			return name;
		}
	}
	/**
	 * @return the hint
	 */
	public String getHint() {
		return hint;
	}

	/**
	 * @param hint the hint to set
	 */
	public void setHint(String hint) {
		this.hint = hint;
	}

	/**
	 * @param moduleId the moduleId to set
	 */
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	/**
	 * @return the moduleId
	 */
	public String getModuleId() {
		return moduleId;
	}

	/**
	 * @param moduleVersion the moduleVersion to set
	 */
	public void setModuleVersion(String moduleVersion) {
		this.moduleVersion = moduleVersion;
	}

	/**
	 * @return the moduleVersion
	 */
	public String getModuleVersion() {
		return moduleVersion;
	}
	
	/**
	 * @return the type
	 */
	public TYPE getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(TYPE type) {
		this.type = type;
	}

	/**
	 * @return the location
	 */
	public LOCATION getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(LOCATION location) {
		this.location = location;
	}

	/**
	 * @return the autostart
	 */
	public boolean isAutostart() {
		return autostart;
	}

	/**
	 * @param autostart the autostart to set
	 */
	public void setAutostart(boolean autostart) {
		this.autostart = autostart;
	}

	/**
	 * @return the closable
	 */
	public boolean isClosable() {
		return closable;
	}

	/**
	 * @param closable the closable to set
	 */
	public void setClosable(boolean closable) {
		this.closable = closable;
	}
		
}
