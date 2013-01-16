package com.thingtrack.konekti.dao.api;

import java.sql.Connection;
import java.util.List;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.Report;

/**
 * @author Thingtrack S.L.
 *
 */
public interface ReportDao extends Dao<Report, Integer> {
	public Report getByCode (String code ) throws Exception;
	public List<Report> getAllByOrganization (Organization organization ) throws Exception;
	public Connection getConnection() throws Exception;
}
