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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="FEEDBACK")
public class Feedback implements Serializable {
	@Id
	@Column(name="FEEDBACK_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer feedbackId;
	
	@ManyToOne
	@JoinColumn(name="INVOICE_ID")
	private Invoice invoice;
	
	@Column(name="COMMENT", length=512)
	private String comment;
	
	@ManyToOne
	@JoinColumn(name="FEEDBACK_TYPE_ID", nullable=false)
	private FeedbackType feedbackType;
	
	@ManyToOne
	@JoinColumn(name="FEEDBACK_STATUS_ID", nullable=false)
	private FeedbackStatus feedbackStatus;
	
	@Column(name="FEEDBACK_DATE",  nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date feedbackDate = new Date();

	/**
	 * @param feedbackId the feedbackId to set
	 */
	public void setFeedbackId(Integer feedbackId) {
		this.feedbackId = feedbackId;
	}

	/**
	 * @return the feedbackId
	 */
	public Integer getFeedbackId() {
		return feedbackId;
	}

	/**
	 * @param invoice the invoice to set
	 */
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	/**
	 * @return the invoice
	 */
	public Invoice getInvoice() {
		return invoice;
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
	 * @param feedbackStatus the feedbackStatus to set
	 */
	public void setFeedbackStatus(FeedbackStatus feedbackStatus) {
		this.feedbackStatus = feedbackStatus;
	}

	/**
	 * @return the feedbackStatus
	 */
	public FeedbackStatus getFeedbackStatus() {
		return feedbackStatus;
	}

	/**
	 * @param feedbackDate the feedbackDate to set
	 */
	public void setFeedbackDate(Date feedbackDate) {
		this.feedbackDate = feedbackDate;
	}

	/**
	 * @return the feedbackDate
	 */
	public Date getFeedbackDate() {
		return feedbackDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((feedbackId == null) ? 0 : feedbackId.hashCode());
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
		if (!(obj instanceof Feedback))
			return false;
		Feedback other = (Feedback) obj;
		if (feedbackId == null) {
			if (other.feedbackId != null)
				return false;
		} else if (!feedbackId.equals(other.feedbackId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Feedback [feedbackId=" + feedbackId + ", comment=" + comment 
				+ ", feedbackStatus=" + feedbackStatus
				+ ", feedbackDate=" + feedbackDate + "]";
	}

	/**
	 * @return the feedbackType
	 */
	public FeedbackType getFeedbackType() {
		return feedbackType;
	}

	/**
	 * @param feedbackType the feedbackType to set
	 */
	public void setFeedbackType(FeedbackType feedbackType) {
		this.feedbackType = feedbackType;
	}
}
