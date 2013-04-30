package com.thingtrack.konekti.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Agent implements Serializable {
	@Id
	@Column(name = "AGENT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer agentId;

	@Column(name = "NAME", length = 64)
	private String name;

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
	@JoinColumn(name = "ADDRESS_ID")
	private Address address;
	
	@Column(name = "COMMENT", length = 512)
	private String comment;

	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="USER_ID")		
	private User user;
		
	/**
	 * @return the agentId
	 */
	public Integer getAgentId() {
		return agentId;
	}

	/**
	 * @param agentId the agentId to set
	 */
	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
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

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Agent [agentId=" + agentId + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agentId == null) ? 0 : agentId.hashCode());
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
		Agent other = (Agent) obj;
		if (agentId == null) {
			if (other.agentId != null)
				return false;
		} else if (!agentId.equals(other.agentId))
			return false;
		return true;
	}

}
