package com.thingtrack.konekti.service.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.api.ApplicationDao;
import com.thingtrack.konekti.domain.Application;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.User;
import com.thingtrack.konekti.service.api.ApplicationService;

public class ApplicationServiceImpl implements ApplicationService {
	@Autowired
	private ApplicationDao applicationDao;
	
	@Override
	public List<Application> getAll() throws Exception {
		return this.applicationDao.getAll();
	}
	
	@Override
	public Application get(Integer applicationId) throws Exception {
		return this.applicationDao.get(applicationId);
	}

	@Override
	public Application getByName(String name) throws Exception {
		return this.applicationDao.getByName(name);
	}

	@Override
	public Application save(Application application) throws Exception {
		return this.applicationDao.save(application);
	}

	@Override
	public void delete(Application application) throws Exception {
		this.applicationDao.delete(application);
		
	}

	@Override
	public Application createNewEntity(Organization organization) throws Exception {
		Application application = new Application();

		application.setOrganization(organization);

		return application;
	}
	
	@Override
	public List<Application> getAll(User user) throws Exception {
		return this.applicationDao.getAll(user);
		
	}
	
}
