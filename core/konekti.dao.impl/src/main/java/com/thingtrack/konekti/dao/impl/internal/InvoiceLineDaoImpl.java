package com.thingtrack.konekti.dao.impl.internal;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.dao.api.InvoiceLineDao;
import com.thingtrack.konekti.domain.InvoiceLine;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class InvoiceLineDaoImpl extends JpaDao<InvoiceLine, Integer> implements InvoiceLineDao {
	@Override
	public InvoiceLine getByNumber(Integer number) throws Exception {
		InvoiceLine invoiceLine = (InvoiceLine)getEntityManager()
		.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.number = :number")
		.setParameter("number", number).getSingleResult();
		
		return invoiceLine;

	}

}
