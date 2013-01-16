package com.thingtrack.konekti.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="ATTACHMENT")
public class Attachment implements Serializable {
	@Id
	@Column(name="ATTACHMENT_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer attachmentId;
	
	@Column(name="ENTITY_NAME")
	private String entityName;
	
	@Column(name="ENTITY_ID")
	private Integer entityId;
	
	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="COMMENT")
	private String comment;
	
	@Column(name="FILE")
	@Lob
	private byte[] file;
	
	public Attachment() {}
	
	public Attachment(String entityName, Integer entityId, String fileName, String comment, byte[] file) {
		this.entityName =entityName;
		this.entityId = entityId;
		this.fileName = fileName;
		this.comment = comment;
		this.file = file;
		 
	}
	/**
	 * @return the attachmentId
	 */
	public Integer getAttachmentId() {
		return attachmentId;
	}

	/**
	 * @param attachmentId the attachmentId to set
	 */
	public void setAttachmentId(Integer attachmentId) {
		this.attachmentId = attachmentId;
	}

	/**
	 * @return the entityName
	 */
	public String getEntityName() {
		return entityName;
	}

	/**
	 * @param entityName the entityName to set
	 */
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	/**
	 * @return the entityId
	 */
	public Integer getEntityId() {
		return entityId;
	}

	/**
	 * @param entityId the entityId to set
	 */
	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
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
	 * @return the file
	 */
	public byte[] getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(byte[] file) {
		this.file = file;
	}
}
