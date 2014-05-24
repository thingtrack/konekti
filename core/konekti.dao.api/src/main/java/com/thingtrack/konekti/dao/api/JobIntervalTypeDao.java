package com.thingtrack.konekti.dao.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.JobIntervalType;

/**
 * Job Trigger Type Data Access Layer
 * @author carlos
 *
 */
public interface JobIntervalTypeDao extends Dao<JobIntervalType, Integer> {
	
	/**
	 * Obtains {@link JobIntervalType} by its code
	 * @param code, unique and not null
	 * @return @JobTriggerType by its code
	 * @throws Exception if there is no result found
	 */
	public JobIntervalType getByCode(String code) throws Exception;
}
