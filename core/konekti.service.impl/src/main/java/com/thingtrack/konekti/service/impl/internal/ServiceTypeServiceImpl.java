package com.thingtrack.konekti.service.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.api.ServiceTypeDao;
import com.thingtrack.konekti.domain.ServiceType;
import com.thingtrack.konekti.service.api.ServiceTypeService;

/**
 * @author Thingtrack S.L.
 *
 */
public class ServiceTypeServiceImpl implements ServiceTypeService {
	@Autowired
	private ServiceTypeDao serviceTypeDao;

	@Override
	public List<ServiceType> getAll() throws Exception {
		return this.serviceTypeDao.getAll();
		
	}

	@Override
	public ServiceType get(Integer serviceTypeId) throws Exception {
		return this.serviceTypeDao.get(serviceTypeId);
		
	}

	@Override
	public ServiceType getByCode(String code) throws Exception {
		return this.serviceTypeDao.getByCode(code);
		
	}

	@Override
	public ServiceType save(ServiceType serviceType) throws Exception {
		return this.serviceTypeDao.save(serviceType);
		
	}

	@Override
	public void delete(ServiceType serviceType) throws Exception {
		this.serviceTypeDao.delete(serviceType);
		
	}

}
