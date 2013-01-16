package com.thingtrack.konekti.service.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.api.ServiceStatusDao;
import com.thingtrack.konekti.domain.ServiceStatus;
import com.thingtrack.konekti.service.api.ServiceStatusService;

/**
 * @author Thingtrack S.L.
 *
 */
public class ServiceStatusServiceImpl implements ServiceStatusService {
	@Autowired
	private ServiceStatusDao serviceStatusDao;

	@Override
	public List<ServiceStatus> getAll() throws Exception {
		return this.serviceStatusDao.getAll();
		
	}

	@Override
	public ServiceStatus get(Integer serviceStatusId) throws Exception {
		return this.serviceStatusDao.get(serviceStatusId);
		
	}

	@Override
	public ServiceStatus getByCode(String code) throws Exception {
		return this.serviceStatusDao.getByCode(code);
		
	}

	@Override
	public ServiceStatus save(ServiceStatus serviceStatus) throws Exception {
		return this.serviceStatusDao.save(serviceStatus);
		
	}

	@Override
	public void delete(ServiceStatus serviceStatus) throws Exception {
		this.serviceStatusDao.delete(serviceStatus);
		
	}

}
