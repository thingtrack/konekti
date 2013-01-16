package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.WorkshopType;

/**
 * @author Thingtrack S.L.
 *
 */
public interface WorkshopTypeService {
	public List<WorkshopType> getAll() throws Exception;
	public WorkshopType get( Integer workshopId ) throws Exception;
	public WorkshopType getByCode( String code ) throws Exception;
	public WorkshopType save(WorkshopType workshopType) throws Exception;
	public void delete(WorkshopType workshopType) throws Exception;
}
