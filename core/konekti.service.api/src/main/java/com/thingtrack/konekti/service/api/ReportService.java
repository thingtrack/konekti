package com.thingtrack.konekti.service.api;

import java.sql.Connection;
import java.util.List;

import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.Report;

public interface ReportService {
	public List<Report> getAll() throws Exception;
	public Report get( Integer reportId ) throws Exception;
	public Report getByCode( String code ) throws Exception;
	public Report save(Report report) throws Exception;
	public void delete(Report report) throws Exception;
	public List<Report> getAllByOrganization(Organization organization) throws Exception;
	public Report createNewReport(Organization organization) throws Exception;
	public Connection getConnection() throws Exception;
}
