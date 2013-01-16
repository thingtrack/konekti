package com.thingtrack.konekti.service.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.api.IvaDao;
import com.thingtrack.konekti.domain.Iva;
import com.thingtrack.konekti.service.api.IvaService;

/**
 * @author Thingtrack S.L.
 *
 */
public class IvaServiceImpl implements IvaService {
	@Autowired
	private IvaDao ivaDao;

	@Override
	public List<Iva> getAll() throws Exception {
		return this.ivaDao.getAll();
		
	}

	@Override
	public Iva get(Integer ivaId) throws Exception {
		return this.ivaDao.get(ivaId);
		
	}

	@Override
	public Iva getByCode(String code) throws Exception {
		return this.ivaDao.getByCode(code);
		
	}

	@Override
	public Iva save(Iva iva) throws Exception {
		return this.ivaDao.save(iva);
		
	}

	@Override
	public void delete(Iva iva) throws Exception {
		this.ivaDao.delete(iva);
		
	}
	

}
