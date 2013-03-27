package com.thingtrack.konekti.service.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.api.AreaTypeDao;
import com.thingtrack.konekti.domain.AreaType;
import com.thingtrack.konekti.service.api.AreaTypeService;

/**
 * @author Thingtrack S.L.
 *
 */
public class AreaTypeServiceImpl implements AreaTypeService {
	@Autowired
	private AreaTypeDao areaTypeDao;

	@Override
	public List<AreaType> getAll() throws Exception {
		return this.areaTypeDao.getAll();
		
	}

	@Override
	public AreaType get(Integer areaTypeId) throws Exception {
		return this.areaTypeDao.get(areaTypeId);
		
	}

	@Override
	public AreaType getByName(String name) throws Exception {
		return this.areaTypeDao.getByName(name);
		
	}

	@Override
	public AreaType save(AreaType areaType) throws Exception {
		return this.areaTypeDao.save(areaType);
		
	}

	@Override
	public void delete(AreaType areaType) throws Exception {
		this.areaTypeDao.delete(areaType);
		
	}
	
}
