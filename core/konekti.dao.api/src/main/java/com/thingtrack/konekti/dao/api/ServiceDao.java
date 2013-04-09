package com.thingtrack.konekti.dao.api;

import java.util.List;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.Service;

/**
 * @author Thingtrack S.L.
 *
 */
public interface ServiceDao extends Dao<Service, Integer> {
	public Service getByCode(String code) throws Exception;
	public List<Service> getAll(Organization organization) throws Exception;
	public List<Service> getAllTemplates(Organization organization) throws Exception;
}
