package com.thingtrack.konekti.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="INVOICE_LINE_STATUS")
public class InvoiceLineStatus implements Serializable {
	@Id
	@Column(name="INVOICE_LINE_STATUS_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer invoiceLineStatusId;
		
	@Column(name="CODE", nullable=false, unique=true, length=64)
	private String code;
	
	@Column(name="DESCRIPTION", length=512)
	private String description;

	/**
	 * @return the invoiceLineStatusId
	 */
	public Integer getInvoiceLineStatusId() {
		return invoiceLineStatusId;
	}

	/**
	 * @param invoiceLineStatusId the invoiceLineStatusId to set
	 */
	public void setInvoiceLineStatusId(Integer invoiceLineStatusId) {
		this.invoiceLineStatusId = invoiceLineStatusId;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime
				* result
				+ ((invoiceLineStatusId == null) ? 0 : invoiceLineStatusId
						.hashCode());
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
		if (!(obj instanceof InvoiceLineStatus))
			return false;
		InvoiceLineStatus other = (InvoiceLineStatus) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (invoiceLineStatusId == null) {
			if (other.invoiceLineStatusId != null)
				return false;
		} else if (!invoiceLineStatusId.equals(other.invoiceLineStatusId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InvoiceLineStatus [invoiceLineStatusId=" + invoiceLineStatusId
				+ ", code=" + code + ", description=" + description + "]";
	}
}
