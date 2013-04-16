package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.Configuration;
import com.thingtrack.konekti.domain.MenuResource;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.User;

public interface ConfigurationService {
	public List<Configuration> getAll() throws Exception;
	public Configuration get( Integer configurationId ) throws Exception;
	public Configuration save(Configuration configuration) throws Exception;
	public void delete(Configuration configuration) throws Exception;
	public Configuration getByTag( String tag ) throws Exception;
	public List<Configuration> getAll(User user) throws Exception;
	public Configuration getByTag(User user, String tag, MenuResource menuResource) throws Exception;
	public Configuration createNewEntity(Organization organization) throws Exception;
}
