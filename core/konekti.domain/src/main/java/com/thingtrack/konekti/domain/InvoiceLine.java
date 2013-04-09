package com.thingtrack.konekti.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="INVOICE_LINE")
public class InvoiceLine implements Serializable {
	@Id
	@Column(name="INVOICE_LINE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer invoiceLineId;
	
	@Column(name="NUMBER", nullable=false)
	private Integer number;
	
	@ManyToOne
	@JoinColumn(name="INVOICE_ID", nullable=false)
	private Invoice invoice;
	
	@OneToMany(mappedBy="invoiceLine")
	private List<Service> services = new ArrayList<Service>();
	
	@Column(name="PRICE", length = 10, precision = 2)
	private double price;
	
	@Column(name="DISCOUNT", length = 5, precision = 2)
	private double discount;
	
	@ManyToOne
	@JoinColumn(name="IVA_ID")
	private Tax iva;
	
	@Column(name="PRICE_TAX", length = 5, precision = 2)
	private double priceFinal;
	
	@Column(name="COMMENT", length=512)
	private String comment;
	
	@ManyToOne
	@JoinColumn(name="INVOICE_LINE_STATUS_ID", nullable=false)
	private InvoiceLineStatus invoiceLineStatus;
	
	@Column(name="INVOICE_LINE_DATE", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date invoiceLineDate;

	public enum STATUS {        
        OPENED,
        SENT,
        CLOSED
    }
	
	/**
	 * @return the invoiceLineId
	 */
	public Integer getInvoiceLineId() {
		return invoiceLineId;
	}

	/**
	 * @param invoiceLineId the invoiceLineId to set
	 */
	public void setInvoiceLineId(Integer invoiceLineId) {
		this.invoiceLineId = invoiceLineId;
	}

	/**
	 * @return the number
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * @return the invoice
	 */
	public Invoice getInvoice() {
		return invoice;
	}

	/**
	 * @param invoice the invoice to set
	 */
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	/**
	 * @return the services
	 */
	public List<Service> getServices() {
		return services;
	}

	/**
	 * @param services the services to set
	 */
	public void setServices(List<Service> services) {
		this.services = services;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the discount
	 */
	public double getDiscount() {
		return discount;
	}

	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
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
	 * @return the invoiceLineStatus
	 */
	public InvoiceLineStatus getInvoiceLineStatus() {
		return invoiceLineStatus;
	}

	/**
	 * @param invoiceLineStatus the invoiceLineStatus to set
	 */
	public void setInvoiceLineStatus(InvoiceLineStatus invoiceLineStatus) {
		this.invoiceLineStatus = invoiceLineStatus;
	}

	/**
	 * @return the invoiceLineDate
	 */
	public Date getInvoiceLineDate() {
		return invoiceLineDate;
	}

	/**
	 * @param invoiceLineDate the invoiceLineDate to set
	 */
	public void setInvoiceLineDate(Date invoiceLineDate) {
		this.invoiceLineDate = invoiceLineDate;
	}

	/**
	 * @return the iva
	 */
	public Tax getIva() {
		return iva;
	}

	/**
	 * @param iva the iva to set
	 */
	public void setIva(Tax iva) {
		this.iva = iva;
	}

	/**
	 * @return the priceFinal
	 */
	public double getPriceFinal() {
		return priceFinal;
	}

	/**
	 * @param priceFinal the priceFinal to set
	 */
	public void setPriceFinal(double priceFinal) {
		this.priceFinal = priceFinal;
	}
}
