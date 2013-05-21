package com.thingtrack.konekti.service.impl.internal;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.api.JobDao;
import com.thingtrack.konekti.domain.Job;
import com.thingtrack.konekti.domain.User;
import com.thingtrack.konekti.service.api.JobService;

public class JobServiceImpl implements JobService {
	@Autowired
	private JobDao jobDao;
	
	@Override
	public List<Job> getAll() throws Exception {
		return this.jobDao.getAll();
	}

	@Override
	public Job get(Integer jobId) throws Exception {
		return this.jobDao.get(jobId);
	}

	@Override
	public Job save(Job job) throws Exception {
		return this.jobDao.save(job);
	}

	@Override
	public void delete(Job job) throws Exception {
		this.jobDao.delete(job);
		
	}

	@Override
	public List<Job> getAll(User user) throws Exception {
		return this.jobDao.getAll(user);
		
	}
	
	@Override
	public Job getByGroupName(String group, String name) throws Exception {
		return jobDao.getByGroupName(group, name);
	}

	@Override
	public void setLastExecution(Job job) throws Exception {
		job.setLastExecution(new Date());
		
		jobDao.save(job);
	}
	
	@Override
	public void setLastExecution(Job job, Boolean error) throws Exception {
		job.setLastExecution(new Date());
		job.setError(error);
		
		jobDao.save(job);
	}
	
	@Override
	public void setOkStatus(Job job) throws Exception {
		job.setError(false);
		
		jobDao.save(job);
	}
	
	@Override
	public void setErrorStatus(Job job) throws Exception {
		job.setError(true);
		
		jobDao.save(job);
	}
}
