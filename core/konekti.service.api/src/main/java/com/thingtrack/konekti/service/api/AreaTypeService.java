package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.AreaType;

public interface AreaTypeService {
	public List<AreaType> getAll() throws Exception;
	public AreaType get( Integer areaTypeId ) throws Exception;
	public AreaType getByName( String name ) throws Exception;
	public AreaType save(AreaType areaType) throws Exception;
	public void delete(AreaType areaType) throws Exception;
}
