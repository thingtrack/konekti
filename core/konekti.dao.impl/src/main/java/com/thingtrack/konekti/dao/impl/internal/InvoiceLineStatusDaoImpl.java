package com.thingtrack.konekti.dao.impl.internal;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.dao.api.InvoiceLineStatusDao;
import com.thingtrack.konekti.domain.InvoiceLineStatus;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class InvoiceLineStatusDaoImpl extends JpaDao<InvoiceLineStatus, Integer> implements InvoiceLineStatusDao {
	@Override
	public InvoiceLineStatus getByCode(String code) throws Exception {
		InvoiceLineStatus invoiceLineStatus = (InvoiceLineStatus)getEntityManager()
		.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.code = :code")
		.setParameter("code", code).getSingleResult();
		
		return invoiceLineStatus;
		
	}

}
