package com.thingtrack.konekti.service.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.api.TaxDao;
import com.thingtrack.konekti.domain.Tax;
import com.thingtrack.konekti.service.api.TaxService;

/**
 * @author Thingtrack S.L.
 *
 */
public class TaxServiceImpl implements TaxService {
	@Autowired
	private TaxDao taxDao;

	@Override
	public List<Tax> getAll() throws Exception {
		return this.taxDao.getAll();
		
	}

	@Override
	public Tax get(Integer taxId) throws Exception {
		return this.taxDao.get(taxId);
		
	}

	@Override
	public Tax getByCode(String code) throws Exception {
		return this.taxDao.getByCode(code);
		
	}

	@Override
	public Tax save(Tax tax) throws Exception {
		return this.taxDao.save(tax);
		
	}

	@Override
	public void delete(Tax tax) throws Exception {
		this.taxDao.delete(tax);
		
	}
	

}
