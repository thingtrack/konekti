package com.thingtrack.konekti.filter.service.api;

import java.util.List;

import com.thingtrack.konekti.dao.template.DaoFilter;
import com.thingtrack.konekti.domain.Client;

public interface FilterClientService {
	public List<DaoFilter> getFilters() throws Exception;
	public DaoFilter getByAttributeName(String name);
	
	public List<Client> getFiltered(List<DaoFilter> filters) throws Exception;
}
