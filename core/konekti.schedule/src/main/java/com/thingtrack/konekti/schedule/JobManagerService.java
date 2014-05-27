package com.thingtrack.konekti.schedule;

import com.thingtrack.konekti.domain.Job;

public interface JobManagerService {
	public void scheduleJob(Job job) throws Exception;
	public void deleteJob(Job job) throws Exception;
	public void resumeJob(Job job) throws Exception;
	public void pauseJob(Job job) throws Exception;
	public void configure(Job job) throws Exception;
}
