package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.JobTriggerType;

public interface JobTriggerTypeService {
	public List<JobTriggerType> getAll() throws Exception;
	public JobTriggerType get( Integer jobTriggerTypeId ) throws Exception;
	public JobTriggerType getByCode( String code ) throws Exception;
	public JobTriggerType save(JobTriggerType jobTriggerType) throws Exception;
	public void delete(JobTriggerType jobTriggerType) throws Exception;
}
