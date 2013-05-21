package com.thingtrack.konekti.dao.api;

import java.sql.Connection;
import java.util.List;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.Report;
import com.thingtrack.konekti.domain.User;

/**
 * @author Thingtrack S.L.
 *
 */
public interface ReportDao extends Dao<Report, Integer> {
	public Report getByCode (String code ) throws Exception;
	public List<Report> getAll(User user) throws Exception;
	public Connection getConnection() throws Exception;
}
