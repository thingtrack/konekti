package com.thingtrack.konekti.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Thingtrack S.L.
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "CONFIGURATION")
public class Configuration implements Serializable {
	@Id
	@Column(name = "CONFIGURATION_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer configurationId;
	
	@Column(name = "TAG", nullable = false, length = 45)
	private String tag;
	
	@Column(name = "TYPE", nullable = false, length = 512)
	private String type;
	
	@Column(name = "VALUE", nullable = false, length = 64)
	private String value;
	
	@Column(name = "DESCRIPTION", nullable = false, length = 64)
	private String description;
	
	@ManyToOne
	@JoinColumn(name="ORGANIZATION_ID", nullable=true)	
	private Organization organization;

	@ManyToOne
	@JoinColumn(name="MENU_RESOURCE_ID", nullable=true)
	private MenuCommandResource menuCommandResource;

	public enum TAG {        
        NAME,
        VERSION,
        FAVICON,
        LOGO_INIT,
        LOGO_WORKBENCH,
        DEMO
    }
	public enum TYPE {
		STRING("String"),
		BOOLEAN("Boolean"),
		INTEGER("Integer"),
		DOUBLE("Double"),
		DATE("Date");
		
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
	 * @return the configurationId
	 */
	public Integer getConfigurationId() {
		return configurationId;
	}

	/**
	 * @param configurationId the configurationId to set
	 */
	public void setConfigurationId(Integer configurationId) {
		this.configurationId = configurationId;
	}

	/**
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * @param tag the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
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

	/**
	 * @return the menuResouce
	 */
	public MenuCommandResource getMenuCommandResource() {
		return menuCommandResource;
	}

	/**
	 * @param menuResouce the menuResouce to set
	 */
	public void setMenuCommandResource(MenuCommandResource menuResouce) {
		this.menuCommandResource = menuResouce;
	}

}
