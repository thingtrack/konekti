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

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="EMPLOYEE_AGENT")
public class EmployeeAgent implements Serializable {
	@Id
	@Column(name="EMPLOYEE_AGENT_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer employeeAgentId;
	
	@Column(name="NAME", nullable=false, length=64)
	private String name;
	
	@Column(name="SURNAME", length=64)
	private String surname;
	
	@Column(name="SHORTNAME", length=64)
	private String shortname;
	
	@Column(name="WORKNUMBER", unique=true, length=64)
	private String workNumber;
	
	@ManyToOne
	@JoinColumn(name="EMPLOYEE_AGENT_TYPE_ID", nullable=false)		
	private EmployeeAgentType employeeAgentType;
	
	@Column(name="NIF", length=64)
	private String nif;

	@ManyToOne
	@JoinColumn(name="EMPLOYEE_ADDRESS_ID")	
	private Address employeeAddress;
	
	@Column(name="PHONE", length=32)
	private String phone;
	
	@Column(name="FAX", length=32)
	private String fax;
	
	@Column(name="MOBILE", length=32)
	private String mobile;
	
	@Column(name="EMAIL", length=32)
	private String email;
	
	@Column(name="PHOTO")
	@Lob
	private byte[] photo;
	
	@Column(name="FACEBOOK_ID", length=32)
	private String facebookId;
	
	@Column(name="TITTLE", length=32)
	private String tittle;
	
	@Column(name="WORK_MOBILE", length=32)
	private String workMobile;
	
	@Column(name="SENIORITY")
	@Temporal(TemporalType.DATE)
	private Date seniority;
	
	@Column(name="BIRTHDAY", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	@ManyToMany
	@JoinTable(name="EMPLOYEE_AGENT_ORGANIZATION",
			   joinColumns=@JoinColumn(name="EMPLOYEE_AGENT_ID"),
			   inverseJoinColumns=@JoinColumn(name="ORGANIZATION_ID"))		
	private List<Organization> organizations = new ArrayList<Organization>();
	
	@ManyToMany
	@JoinTable(name="EMPLOYEE_AGENT_LOCATION",
			   joinColumns=@JoinColumn(name="EMPLOYEE_AGENT_ID"),
			   inverseJoinColumns=@JoinColumn(name="LOCATION_ID"))		
	private List<Location> locations = new ArrayList<Location>();
	
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="USER_ID")		
	private User user;
	
	@Column(name="COMMENT", length=512)
	private String comment;
		
	@OneToMany(mappedBy="driver")
	private List<OfferRequestLine> offerRequestLines;
	
	@ManyToOne
	@JoinColumn(name="EMPLOYEE_AGENT_STATUS_ID", nullable=false)
	private EmployeeAgentStatus employeeAgentStatus;

	public enum EMPLOYEE_AGENT_TYPE{		
		OFFICER_TYPE_1,
		OFFICER_TYPE_2,
		OPERATOR_TYPE_1,
		OPERATOR_TYPE_2,
		MANAGER_TYPE_1,
		MANAGER_TYPE_2
	} 
	
	/**
	 * @param employeeAgentId the employeeAgentId to set
	 */
	public void setEmployeeAgentId(Integer employeeAgentId) {
		this.employeeAgentId = employeeAgentId;
	}

	/**
	 * @return the employeeAgentId
	 */
	public Integer getEmployeeAgentId() {
		return employeeAgentId;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param workNumber the workNumber to set
	 */
	public void setWorkNumber(String workNumber) {
		this.workNumber = workNumber;
	}

	/**
	 * @return the workNumber
	 */
	public String getWorkNumber() {
		return workNumber;
	}

	/**
	 * @param employeeAgentType the employeeAgentType to set
	 */
	public void setEmployeeAgentType(EmployeeAgentType employeeAgentType) {
		this.employeeAgentType = employeeAgentType;
	}

	/**
	 * @return the employeeAgentType
	 */
	public EmployeeAgentType getEmployeeAgentType() {
		return employeeAgentType;
	}

	/**
	 * @param nif the nif to set
	 */
	public void setNif(String nif) {
		this.nif = nif;
	}

	/**
	 * @return the nif
	 */
	public String getNif() {
		return nif;
	}

	/**
	 * @param employeeAddress the employeeAddress to set
	 */
	public void setEmployeeAddress(Address employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

	/**
	 * @return the employeeAddress
	 */
	public Address getEmployeeAddress() {
		return employeeAddress;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	/**
	 * @return the photo
	 */
	public byte[] getPhoto() {
		return photo;
	}

	/**
	 * @param facebookId the facebookId to set
	 */
	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	/**
	 * @return the facebookId
	 */
	public String getFacebookId() {
		return facebookId;
	}

	/**
	 * @param tittle the tittle to set
	 */
	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	/**
	 * @return the tittle
	 */
	public String getTittle() {
		return tittle;
	}

	/**
	 * @param workMobile the workMobile to set
	 */
	public void setWorkMobile(String workMobile) {
		this.workMobile = workMobile;
	}

	/**
	 * @return the workMobile
	 */
	public String getWorkMobile() {
		return workMobile;
	}

	/**
	 * @param organizations the organizations to set
	 */
	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}

	/**
	 * @return the organizations
	 */
	public List<Organization> getOrganizations() {
		return organizations;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param employeeAgentStatus the employeeAgentStatus to set
	 */
	public void setEmployeeAgentStatus(EmployeeAgentStatus employeeAgentStatus) {
		this.employeeAgentStatus = employeeAgentStatus;
	}

	/**
	 * @return the employeeAgentStatus
	 */
	public EmployeeAgentStatus getEmployeeAgentStatus() {
		return employeeAgentStatus;
	}

	/**
	 * @param seniority the seniority to set
	 */
	public void setSeniority(Date seniority) {
		this.seniority = seniority;
	}

	/**
	 * @return the seniority
	 */
	public Date getSeniority() {
		return seniority;
	}

	/**
	 * @param shortname the shortname to set
	 */
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	/**
	 * @return the shortname
	 */
	public String getShortname() {
		return shortname;
	}

	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param offerRequestLines the offerRequestLines to set
	 */
	public void setOfferRequestLines(List<OfferRequestLine> offerRequestLines) {
		this.offerRequestLines = offerRequestLines;
	}

	/**
	 * @return the offerRequestLines
	 */
	public List<OfferRequestLine> getOfferRequestLines() {
		return offerRequestLines;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((employeeAgentId == null) ? 0 : employeeAgentId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeAgent other = (EmployeeAgent) obj;
		if (employeeAgentId == null) {
			if (other.employeeAgentId != null)
				return false;
		} else if (!employeeAgentId.equals(other.employeeAgentId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EmployeeAgent [employeeAgentId=" + employeeAgentId + "]";
	}
	
	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the locations
	 */
	public List<Location> getLocations() {
		return locations;
	}

	/**
	 * @param locations the locations to set
	 */
	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

}
