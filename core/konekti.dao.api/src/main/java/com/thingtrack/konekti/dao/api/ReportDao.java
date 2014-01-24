package com.thingtrack.konekti.dao.api;

import java.sql.Connection;
import java.util.List;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.Report;
import com.thingtrack.konekti.domain.User;

/**
 * Report Data Access Layer
 * <p>
 * @author Thingtrack S.L.
 *
 */
public interface ReportDao extends Dao<Report, Integer> {
	
	/**
	 * Obtains the {@link Report} by its organization belongs to and its unique code
	 *  
	 * @param organization  the organization belongs to, not null
	 * @param code the unique code, not null
	 * @return {@link Report} queryied
	 * @throws Exception if the any given parameter is null
	 */
	public Report getByCode (Organization organization, String code ) throws Exception;
	/**
	 * Obtains a collection of {@link Report reports} associated to a {@code User}
	 * 
	 * @param user the user, not null
	 * @return 
	 * @throws Exception
	 */
	public List<Report> getAll(User user) throws Exception;
	
	/**
	 * 
	 * Obtains the current Jasper Report connection
	 * 
	 * @return
	 * @throws Exception
	 */
	public Connection getConnection() throws Exception;
}
