package com.thingtrack.konekti.service.impl.internal;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.api.AlarmJobDao;
import com.thingtrack.konekti.domain.AlarmJob;
import com.thingtrack.konekti.service.api.AlarmJobService;

public class AlarmJobServiceImpl implements AlarmJobService {
	@Autowired
	private AlarmJobDao alarmJobDao;
	
	@Override
	public List<AlarmJob> getAll() throws Exception {
		return this.alarmJobDao.getAll();
	}

	@Override
	public AlarmJob get(Integer alarmJobId) throws Exception {
		return this.alarmJobDao.get(alarmJobId);
	}

	@Override
	public AlarmJob save(AlarmJob alarmJob) throws Exception {
		return this.alarmJobDao.save(alarmJob);
	}

	@Override
	public void delete(AlarmJob alarmJob) throws Exception {
		this.alarmJobDao.delete(alarmJob);
		
	}

	@Override
	public AlarmJob getByGroupName(String group, String name) throws Exception {
		return alarmJobDao.getByGroupName(group, name);
	}

	@Override
	public void setLastExecution(AlarmJob alarmJob) throws Exception {
		alarmJob.setLastExecution(new Date());
		
		alarmJobDao.save(alarmJob);
	}
	
	@Override
	public void setLastExecution(AlarmJob alarmJob, Boolean error) throws Exception {
		alarmJob.setLastExecution(new Date());
		alarmJob.setError(error);
		
		alarmJobDao.save(alarmJob);
	}
	
	@Override
	public void setOkStatus(AlarmJob alarmJob) throws Exception {
		alarmJob.setError(false);
		
		alarmJobDao.save(alarmJob);
	}
	
	@Override
	public void setErrorStatus(AlarmJob alarmJob) throws Exception {
		alarmJob.setError(true);
		
		alarmJobDao.save(alarmJob);
	}
}
