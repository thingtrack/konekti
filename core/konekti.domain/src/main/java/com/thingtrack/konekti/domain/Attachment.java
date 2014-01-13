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

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
/**
 * Entity class
 * <p>
 * Represents any document attachment to any register of any entity 
 * @author carlos
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="ATTACHMENT")
public class Attachment implements Serializable {
	
	/**
	 * Unique identifier
	 */
	@Id
	@Column(name="ATTACHMENT_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer attachmentId;
	
	/**
	 * Entity name owner
	 */
	@Column(name="ENTITY_NAME")
	private String entityName;
	
	/**
	 * Entity identifier owner
	 */
	@Column(name="ENTITY_ID")
	private Integer entityId;
	
	/**
	 * File name
	 */
	@Column(name="FILE_NAME")
	private String fileName;

	/**
	 * Comment
	 */
	@Column(name="COMMENT")
	private String comment;
	
	/**
	 * File content bytes
	 */
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
