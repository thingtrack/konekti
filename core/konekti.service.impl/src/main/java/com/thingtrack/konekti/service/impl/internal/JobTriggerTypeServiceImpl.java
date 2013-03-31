package com.thingtrack.konekti.service.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.api.JobTriggerTypeDao;
import com.thingtrack.konekti.domain.JobTriggerType;
import com.thingtrack.konekti.service.api.JobTriggerTypeService;

public class JobTriggerTypeServiceImpl implements JobTriggerTypeService {
	@Autowired
	private  JobTriggerTypeDao alarmTriggerTypeDao;
	
	@Override
	public List<JobTriggerType> getAll() throws Exception {
		return this.alarmTriggerTypeDao.getAll();
	}

	@Override
	public JobTriggerType get(Integer alarmTriggerTypeId) throws Exception {
		return alarmTriggerTypeDao.get(alarmTriggerTypeId);
	}

	@Override
	public JobTriggerType getByCode(String code) throws Exception {
		return alarmTriggerTypeDao.getByCode(code);
	}

	@Override
	public JobTriggerType save(JobTriggerType alarmTriggerType) throws Exception {
		return this.alarmTriggerTypeDao.save(alarmTriggerType);
	}

	@Override
	public void delete(JobTriggerType alarmTriggerType) throws Exception {
		this.alarmTriggerTypeDao.delete(alarmTriggerType);
		
	}

}
