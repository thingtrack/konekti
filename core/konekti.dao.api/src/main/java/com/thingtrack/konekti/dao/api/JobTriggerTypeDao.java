package com.thingtrack.konekti.dao.api;

import com.thingtrack.konekti.dao.template.Dao;
import com.thingtrack.konekti.domain.JobTriggerType;

/**
 * Job Trigger Type Data Access Layer
 * @author carlos
 *
 */
public interface JobTriggerTypeDao extends Dao<JobTriggerType, Integer> {
	
	/**
	 * Obtains {@link JobTriggerType} by its code
	 * @param code, unique and not null
	 * @return @JobTriggerType by its code
	 * @throws Exception if there is no result found
	 */
	public JobTriggerType getByCode(String code) throws Exception;
}
