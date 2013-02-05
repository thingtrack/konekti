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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="SUPPLIER")
public class Supplier implements Serializable {
	@Id
	@Column(name="SUPPLIER_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer supplierId;
	
	@Column(name="CODE", nullable=false, unique=true, length=64)
	private String code;
	
	@Column(name="NAME", nullable=false)
	private String name;
	
	@Column(name="DESCRIPTION", length=512)
	private String description;
	
	@Column(name="VAT", length=64)
	private String vat;
	
	@Column(name="PHONE", length=32)	
	private String phone;
	
	@Column(name="FAX", length=32)
	private String fax;
	
	@Column(name="MOBILE", length=32)
	private String mobile;
	
	@Column(name="EMAIL", length=64)
	private String email;
	
	@Column(name="PHOTO")
	@Lob
	private byte[] photo;
	
	@Column(name="FACEBOOK_ID", length=32)
	private String facebookId;
	
	@ManyToOne
	@JoinColumn(name="SUPPLIER_ADDRESS_ID")
	private Address supplierAddress;
	
	@ManyToOne
	@JoinColumn(name="SUPPLIER_TYPE_ID", nullable=false)	
	private SupplierType supplierType;

	@ManyToOne
	@JoinColumn(name="SUPPLIER_GROUP_ID")	
	private SupplierGroup supplierGroup;
	
	@ManyToMany
	@JoinTable(name="SUPPLIER_ORGANIZATION",
			   joinColumns=@JoinColumn(name="SUPPLIER_ID"),
			   inverseJoinColumns=@JoinColumn(name="ORGANIZATION_ID"))	
	private List<Organization> organizations = new ArrayList<Organization>();
	
	@ManyToMany
	@JoinTable(name="SUPPLIER_LOCATION",
			   joinColumns=@JoinColumn(name="SUPPLIER_ID"),
			   inverseJoinColumns=@JoinColumn(name="LOCATION_ID"))		
	private List<Location> locations = new ArrayList<Location>();
	
	@Column(name="COMMNET", length=512)	
	private String comment;
	
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="USER_ID")		
	private User user;
	
	@Column(name="ACTIVE", nullable=false)
	private Boolean active=true;

	/**
	 * @param supplierId the supplierId to set
	 */
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	/**
	 * @return the supplierId
	 */
	public Integer getSupplierId() {
		return supplierId;
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

	/**
	 * @param vat the vat to set
	 */
	public void setVat(String vat) {
		this.vat = vat;
	}

	/**
	 * @return the vat
	 */
	public String getVat() {
		return vat;
	}

	/**
	 * @param supplierAddress the supplierAddress to set
	 */
	public void setSupplierAddress(Address supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	/**
	 * @return the supplierAddress
	 */
	public Address getSupplierAddress() {
		return supplierAddress;
	}

	/**
	 * @param supplierType the supplierType to set
	 */
	public void setSupplierType(SupplierType supplierType) {
		this.supplierType = supplierType;
	}

	/**
	 * @return the supplierType
	 */
	public SupplierType getSupplierType() {
		return supplierType;
	}

	/**
	 * @param supplierGroup the supplierGroup to set
	 */
	public void setSupplierGroup(SupplierGroup supplierGroup) {
		this.supplierGroup = supplierGroup;
	}

	/**
	 * @return the supplierGroup
	 */
	public SupplierGroup getSupplierGroup() {
		return supplierGroup;
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
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the photo
	 */
	public byte[] getPhoto() {
		return photo;
	}

	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	/**
	 * @return the facebookId
	 */
	public String getFacebookId() {
		return facebookId;
	}

	/**
	 * @param facebookId the facebookId to set
	 */
	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((supplierId == null) ? 0 : supplierId.hashCode());
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
		Supplier other = (Supplier) obj;
		if (supplierId == null) {
			if (other.supplierId != null)
				return false;
		} else if (!supplierId.equals(other.supplierId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Supplier [supplierId=" + supplierId + "]";
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
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
