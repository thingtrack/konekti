package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.Configuration;

public interface ConfigurationService {
	public List<Configuration> getAll() throws Exception;
	public Configuration get( Integer configurationId ) throws Exception;
	public Configuration getByCode( String code ) throws Exception;
	public Configuration save(Configuration configuration) throws Exception;
	public void delete(Configuration configuration) throws Exception;
}
