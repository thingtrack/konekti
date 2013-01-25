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
 * Copyright (C) 2010 - 2013 Thingtrack s.l.
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
import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="SUPPLIER_AGENT")
public class SupplierAgent implements Serializable {
	@Id
	@Column(name="SUPPLIER_AGENT_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer supplierAgentId;
	
	@Column(name="NAME", nullable=false, length=256)
	private String name;
	
	@Column(name="SURNAME", length=256)
	private String surname;
	
	@Column(name="SHORTNAME", length=64)
	private String shortname;
	
	@ManyToOne
	@JoinColumn(name="SUPPLIER_AGENT_TYPE_ID", nullable=false)
	private SupplierAgentType supplierAgentType;
	
	@Column(name="NIF", length=64)
	private String nif;
	
	@Column(name="TITTLE", length=64)
	private String title;
	
	@ManyToOne
	@JoinColumn(name="SUPPLIER_AGENT_ADDRESS")	
	private Address supplierAgentAddress;
	
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
	
	@Column(name="FACEBOOK_ID", length=64)
	private String facebookId;

	@ManyToOne
	@JoinColumn(name="SUPPLIER_ID", nullable=false)
	private Supplier supplier;
	
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="USER_ID")
	private User user;
	
	@Column(name="COMMENT", length=512)
	private String Comment;
	
	@Column(name="ACTIVE", nullable=false)
	private Boolean active=true;

	/**
	 * @param supplierAgentId the supplierAgentId to set
	 */
	public void setSupplierAgentId(Integer supplierAgentId) {
		this.supplierAgentId = supplierAgentId;
	}

	/**
	 * @return the supplierAgentId
	 */
	public Integer getSupplierAgentId() {
		return supplierAgentId;
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
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param supplierAgentAddress the supplierAgentAddress to set
	 */
	public void setSupplierAgentAddress(Address supplierAgentAddress) {
		this.supplierAgentAddress = supplierAgentAddress;
	}

	/**
	 * @return the supplierAgentAddress
	 */
	public Address getSupplierAgentAddress() {
		return supplierAgentAddress;
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
	 * @param supplier the supplier to set
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	/**
	 * @return the supplier
	 */
	public Supplier getSupplier() {
		return supplier;
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
		Comment = comment;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return Comment;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((supplierAgentId == null) ? 0 : supplierAgentId.hashCode());
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
		if (!(obj instanceof SupplierAgent))
			return false;
		SupplierAgent other = (SupplierAgent) obj;
		if (supplierAgentId == null) {
			if (other.supplierAgentId != null)
				return false;
		} else if (!supplierAgentId.equals(other.supplierAgentId))
			return false;
		return true;
	}

	/**
	 * @return the supplierAgentType
	 */
	public SupplierAgentType getSupplierAgentType() {
		return supplierAgentType;
	}

	/**
	 * @param supplierAgentType the supplierAgentType to set
	 */
	public void setSupplierAgentType(SupplierAgentType supplierAgentType) {
		this.supplierAgentType = supplierAgentType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SupplierAgent [supplierAgentId=" + supplierAgentId + ", name="
				+ name + ", surname=" + surname + ", shortname=" + shortname
				+ ", supplierAgentType=" + supplierAgentType + ", nif=" + nif
				+ ", title=" + title + ", supplierAgentAddress="
				+ supplierAgentAddress + ", phone=" + phone + ", fax=" + fax
				+ ", mobile=" + mobile + ", email=" + email + ", photo="
				+ Arrays.toString(photo) + ", facebookId=" + facebookId
				+ ", supplier=" + supplier + ", user=" + user + ", Comment="
				+ Comment + ", active=" + active + "]";
	}
}
