package com.thingtrack.konekti.service.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.api.WorkshopTypeDao;
import com.thingtrack.konekti.domain.WorkshopType;
import com.thingtrack.konekti.service.api.WorkshopTypeService;

/**
 * @author Thingtrack S.L.
 *
 */
public class WorkshopTypeServiceImpl implements WorkshopTypeService {
	@Autowired
	private WorkshopTypeDao workshopTypeDao;

	@Override
	public List<WorkshopType> getAll() throws Exception {
		return this.workshopTypeDao.getAll();
		
	}

	@Override
	public WorkshopType get(Integer workshopId) throws Exception {
		return this.workshopTypeDao.get(workshopId);
		
	}

	@Override
	public WorkshopType getByCode(String code) throws Exception {
		return this.workshopTypeDao.getByCode(code);
		
	}

	@Override
	public WorkshopType save(WorkshopType workshopType) throws Exception {
		return this.workshopTypeDao.save(workshopType);
	}

	@Override
	public void delete(WorkshopType workshopType) throws Exception {
		this.workshopTypeDao.delete(workshopType);
		
	}
	
}
