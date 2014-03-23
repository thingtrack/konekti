package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.Application;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.User;

public interface ApplicationService {
	public List<Application> getAll() throws Exception;
	public Application get( Integer applicationId ) throws Exception;
	public Application getByName( String name ) throws Exception;
	public Application getByType(Application.TYPE applicationType) throws Exception;
	public Application save(Application application) throws Exception;
	public void delete(Application application) throws Exception;
	public Application createNewEntity(Organization organization) throws Exception;
	public List<Application> getAll(User user) throws Exception;
}
