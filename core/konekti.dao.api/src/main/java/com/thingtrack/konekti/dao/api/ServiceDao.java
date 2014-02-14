package com.thingtrack.konekti.dao.api;

import java.util.List;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.Sequence;
import com.thingtrack.konekti.domain.Service;

/**
 * Service Data Access Layer
 * <p>
 * @author Thingtrack S.L.
 *
 */
public interface ServiceDao extends Dao<Service, Integer> {
	/**
	 * The {@link Service} by its code
	 * @param name the unique code, not null
	 * @return @ {@link Service}
	 * @throws Exception
	 */
	public Service getByCode(String code) throws Exception;
	
	/**
	 * Obtains the collection of {@link Service services} by the {@code Organization} belongs to
	 * @param organization  the organizations belongs to, not null
	 * @return Collection of {@link Service services}
	 * @throws Exception if the given organization is null
	 */
	public List<Service> getAll(Organization organization) throws Exception;
	
	/**
	 * Obtains the collection of {@link Service services} by the {@code Organization} belongs to
	 * adn having active the template flag
	 * 
	 * @param organization  the organizations belongs to, not null
	 * @return Collection of {@link Service services}
	 * @throws Exception if the given organization is null
	 */
	public List<Service> getAllTemplates(Organization organization) throws Exception;
}
