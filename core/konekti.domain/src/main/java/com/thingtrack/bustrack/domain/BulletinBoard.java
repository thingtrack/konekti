package com.thingtrack.bustrack.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//import com.thingtrack.bustrack.domain.internal.TimestampAdapter;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="BULLETIN_BOARD")
public class BulletinBoard implements Serializable {
	@Id
	@Column(name="BULLETIN_BOARD_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer bulletinBoardId;
	
	@Column(name="NAME", nullable=false, length=256)
	private String name;
	
	@Column(name="EMAIL", length=64)
	private String email;
	
	@Column(name="PHONE", length=32, nullable=false)
	private String phone;
	
	@Column(name="MOBILE", length=32)
	private String mobile;
	
	@ManyToOne
	@JoinColumn(name="BULLETIN_TYPE_ID", nullable=false)
	private BulletinType bulletinType;
	
	@Column(name="MESSAGE", length=512)
	private String message;

	@Column(name="BULLETIN_DATE", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date bulletinDate=new Date();
	
	/**
	 * @param bulletinBoardId the bulletinBoardId to set
	 */
	public void setBulletinBoardId(int bulletinBoardId) {
		this.bulletinBoardId = bulletinBoardId;
	}

	/**
	 * @return the bulletinBoardId
	 */
	public int getBulletinBoardId() {
		return bulletinBoardId;
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
	 * @param bulletinType the bulletinType to set
	 */
	public void setBulletinType(BulletinType bulletinType) {
		this.bulletinType = bulletinType;
	}

	/**
	 * @return the bulletinType
	 */
	public BulletinType getBulletinType() {
		return bulletinType;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param bulletinDate the bulletinDate to set
	 */
	public void setBulletinDate(Date bulletinDate) {
		this.bulletinDate = bulletinDate;
	}

	/**
	 * @return the bulletinDate
	 */
	//@XmlJavaTypeAdapter(TimestampAdapter.class)
	public Date getBulletinDate() {
		return bulletinDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bulletinBoardId == null) ? 0 : bulletinBoardId.hashCode());
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
		if (!(obj instanceof BulletinBoard))
			return false;
		BulletinBoard other = (BulletinBoard) obj;
		if (bulletinBoardId == null) {
			if (other.bulletinBoardId != null)
				return false;
		} else if (!bulletinBoardId.equals(other.bulletinBoardId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BulletinBoard [bulletinBoardId=" + bulletinBoardId + ", name="
				+ name + ", email=" + email + ", phone=" + phone + ", mobile="
				+ mobile + ", bulletinType=" + bulletinType + ", message="
				+ message + ", bulletinDate=" + bulletinDate + "]";
	}
			
}
