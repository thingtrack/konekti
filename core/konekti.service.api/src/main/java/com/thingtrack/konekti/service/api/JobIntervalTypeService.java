package com.thingtrack.konekti.service.api;

import java.util.List;

import com.thingtrack.konekti.domain.JobIntervalType;

public interface JobIntervalTypeService {
	public List<JobIntervalType> getAll() throws Exception;
	public JobIntervalType get( Integer jobIntervalTypeId ) throws Exception;
	public JobIntervalType getByCode( String code ) throws Exception;
	public JobIntervalType save(JobIntervalType jobIntervalType) throws Exception;
	public void delete(JobIntervalType jobIntervalType) throws Exception;
}
