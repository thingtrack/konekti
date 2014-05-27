package com.thingtrack.konekti.service.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.api.JobIntervalTypeDao;
import com.thingtrack.konekti.domain.JobIntervalType;
import com.thingtrack.konekti.service.api.JobIntervalTypeService;

public class JobIntervalTypeServiceImpl implements JobIntervalTypeService {
	@Autowired
	private  JobIntervalTypeDao jobIntervalTypeDao;
	
	@Override
	public List<JobIntervalType> getAll() throws Exception {
		return this.jobIntervalTypeDao.getAll();
	}

	@Override
	public JobIntervalType get(Integer jobIntervalTypeId) throws Exception {
		return jobIntervalTypeDao.get(jobIntervalTypeId);
	}

	@Override
	public JobIntervalType getByCode(String code) throws Exception {
		return jobIntervalTypeDao.getByCode(code);
	}

	@Override
	public JobIntervalType save(JobIntervalType jobIntervalType) throws Exception {
		return this.jobIntervalTypeDao.save(jobIntervalType);
	}

	@Override
	public void delete(JobIntervalType jobIntervalType) throws Exception {
		this.jobIntervalTypeDao.delete(jobIntervalType);
		
	}

}
