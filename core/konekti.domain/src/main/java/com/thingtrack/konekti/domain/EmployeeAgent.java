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

import java.util.List;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@AttributeOverrides({
	@AttributeOverride(name="agentId", column=@Column(name="EMPLOYEE_AGENT_ID"))
})
public class EmployeeAgent extends Agent implements Serializable {
	@Column(name="SURNAME", length=64)
	private String surname;
	
	@Column(name="SHORTNAME", length=64)
	private String shortname;
	
	@Column(name="WORKNUMBER", unique=true, length=64)
	private String workNumber;
		
	@Column(name="NIF", length=64)
	private String nif;
	
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
	
	@ManyToOne
	@JoinColumn(name="EMPLOYEE_AGENT_TYPE_ID", nullable=false)		
	private EmployeeAgentType employeeAgentType;
	
	@OneToMany(mappedBy="driver")
	private List<OfferRequestLine> offerRequestLines;
	
	@ManyToOne
	@JoinColumn(name = "ORGANIZATION_ID", nullable = false)
	private Organization organization;
	
	@ManyToOne
	@JoinColumn(name="EMPLOYEE_AGENT_STATUS_ID", nullable=false)
	private EmployeeAgentStatus employeeAgentStatus;

	public enum EMPLOYEE_AGENT_TYPE{		
		MANAGER,
		OFFICER,
		WORKER,
		DRIVER
	} 
	
	public enum STATUS {
		ACTIVE,
		DECLINE
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
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
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
