package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.Location;
import com.thingtrack.konekti.domain.Workshop;

/**
 * @author Thingtrack S.L.
 *
 */
public interface WorkshopService {
	public List<Workshop> getAll() throws Exception;
	public Workshop get( Integer workshopId ) throws Exception;
	public Workshop getByCode( String code ) throws Exception;
	public Workshop save(Workshop workshop) throws Exception;
	public void delete(Workshop workshop) throws Exception;
	public Workshop createEntity(Location location) throws Exception;
}
