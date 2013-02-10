package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.Configuration;
import com.thingtrack.konekti.domain.MenuResource;
import com.thingtrack.konekti.domain.Organization;

public interface ConfigurationService {
	public List<Configuration> getAll() throws Exception;
	public Configuration get( Integer configurationId ) throws Exception;
	public Configuration getByTag( String tag ) throws Exception;
	public Configuration getByTag( String tag, Organization organization, MenuResource menuResource) throws Exception;
	public Configuration save(Configuration configuration) throws Exception;
	public void delete(Configuration configuration) throws Exception;
}
